<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<script src="../js/global.js"></script>
<script type="text/javascript">
	function searchUserInfo() {
		$("#dg").datagrid({
			url:"../selectUser",
			method:'post',
			rownumbers:true,
			singleSelect:true,
			fitColumns:true,
			pagination:true,
			striped:true,
			toolbar:'#usertb',
			pageSize:10,
			queryParams: {
				u_name: $("#adduserName").textbox("getValue"),
				u_isLockout: $("#cc").combobox("getValue"),
				beginCreateTime: $("#beginCreateTime").datebox("getValue"),
				endCreateTime: $("#endCreateTime").datebox("getValue"),
				beginlastLoginTime: $("#beginlastLoginTime").datebox("getValue"),
				endlastLoginTime: $("#endlastLoginTime").datebox("getValue"),
				
			}
		})
	}
	$(function (){
		searchUserInfo();
	})
	/* 点击添加时打开 */
	function addInfo() {
		$("#u_name").textbox({disabled:false});
		$("#adduserForm").form("clear");
		$("#adduser_window").window("open");
	}
	/* 点击修改时打开 */
	function updateInfo(index) {
		$("#u_name").textbox({disabled:true});
		//将当前数据填入表单
		var data = $("#dg").datagrid("getData");
		var row = data.rows[index];
		$("#adduserForm").form("load", row);
		
		$("#adduser_window").window({title:'修改员工'});
		$("#adduser_window").window("open");
	}
	
	/* 提交方法 */
	function submitUserForms() {
		if($("#adduserForm").form("validate")) {
			if($("#u_id").val()>0){
				$.ajax({
					url: '../insertUser',
					method: 'post',
					data:$("#adduserForm").serialize(),
					dataType: 'json',
					success: function(res) {
						if(res.success){
							$.messager.alert("提示信息", res.message);
							$("#adduser_window").panel("close");
							$("#dg").datagrid("reload");
						} else 
							$.messager.alert("提示信息", res.message);
					}
				});
			}else{
				$.ajax({
					method:'post',
					url:'../selectName',
					data:{
						u_name:$("#u_name").textbox("getValue")
					}, 
					dataType:'json',
					success:function(res){
						if(res.success){
							$.ajax({
								url: '../insertUser',
								method: 'post',
								data:$("#adduserForm").serialize(),
								dataType: 'json',
								success: function(res) {
									if(res.success){
										$.messager.alert("提示信息", res.message);
										$("#adduser_window").panel("close");
										$("#dg").datagrid("reload");
									} else {
										$.messager.alert("提示信息", res.message);}
								}
							})
						} else{
							$.messager.alert("提示信息",res.message);
						}
					}
				});		
			}
			
		} else {
			$.messager.alert("提示信息", "请完成所有验证", "info");
		}
	}
	/* 关闭窗口 */
	function clearUserForm() {
		$("#adduser_window").window("close");
	}
	//锁定和解锁用户
	function formatterLockUser(value, row, index) {
		return "<a style='cursor: pointer;' onclick='lockUser(" + index + ")'>锁定用户</a> <a style='cursor: pointer;' onclick='unlockUser(" + index + ")'>解锁用户</a>";
	}
	//设置角色权限
	function formatterSetRole(value, row, index) {
		return "<a style='cursor: pointer;' onclick='showRoles(" + index + ")'>设置</a>";
	}
	//重置密码
	function formatterResetPassword(value, row, index) {
		return "<a style='cursor: pointer;' onclick='resetPassword(" + index + ")'>重置密码</a>";
	}
	//操作用户
	function formatterOPUser(value, row, index) {
		return "<a style='cursor: pointer;' onclick='updateInfo(" + index + ")'>编辑</a> <a style='cursor: pointer;' onclick='deleteInfo(" + index + ")'>删除</a>";
	}
	function deleteInfo(index) {
		var data = $("#dg").datagrid("getData");
		var row = data.rows[index];
		$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			if(r) { // 用户点击了确认按钮
				$.ajax({
					method: 'post',
					url: '../deleteUser',
					data: {
						"u_id": row.u_id,
					},
					success: function(res) {
						if(res>0){
							$("#dg").datagrid("reload");
							$.messager.alert("提示信息", "成功删除");
						}else
							$.messager.alert("错误信息", "删除失败", "info");
					}
				})
			}
		});
	}
	var yhid;
	var yhna;
	function showRoles(index) {
		var data = $("#dg").datagrid("getData");
		var row = data.rows[index];
		yhna=row.u_name;
		yhid=row.u_id;  
		$("#allUserRoles").datagrid({
			url:"../selectRoles",
			queryParams: { //要发送的参数列表
				u_id:row.u_id
			}
		})
		$("#allRoles").datagrid({
			url:"../selectRoles",
			queryParams: { //要发送的参数列表
			}
		})
		$("#diaUserRoles").dialog({
			title:'您正在设置--'+yhna+'--的权限'
		})
		$("#diaUserRoles").window("open");
	}

	function lockUser(index) {
		var data = $("#dg").datagrid("getData");
		var row = data.rows[index];
		$.messager.confirm('确认', '您确认想要锁定--' + row.u_name + '--用户？', function(r) {
			if(r) {
				$.ajax({
					type: "post",
					url: "../lockUser",
					dataType: 'json',
					data: {
						u_isLockout:2,
						u_id:row.u_id
					},
					success: function(res) {
						if(res>0) {
							$("#dg").datagrid("reload");
							$.messager.alert("提示信息", "锁定成功");
						} else
							$.messager.alert("错误信息", "锁定失败");
					}
				});
			}
		});
	}

	function unlockUser(index) {
		var data = $("#dg").datagrid("getData");
		var row = data.rows[index];
		
		$.messager.confirm('确认', '您确认要为--' + row.u_name + '--解锁？', function(r) {
			if(r) {
				$.ajax({
					type: "post",
					url:"../lockUser",
					dataType: 'json',
					data: {
						u_isLockout:1,
						u_id:row.u_id
					},
					success: function(res) {
						if(res>0) {
							$("#dg").datagrid("reload");
							$.messager.alert("提示信息", "解锁成功");
						} else
							$.messager.alert("错误信息", "解锁失败");
					}
				});
			}
		});
	}
	
	function resetPassword(index){
		var data = $("#dg").datagrid("getData");
		var row = data.rows[index];
		$.messager.confirm('确认', '您确认要把--' + row.u_name + '--的密码恢复默认密码吗？', function(r) {
			if(r) {
				$.ajax({
					type: "post",
					url:"../lockUser",
					dataType: 'json',
					data: {
						u_pwd:'ysd123',
						u_id:row.u_id
					},
					success: function(res) {
						if(res>0) {
							$.messager.alert("提示信息", "恢复成功");
						} else
							$.messager.alert("错误信息", "恢复成功");
					}
				});
			}
		});
	}
	function adddiaUserRoles(){
		var nodes = $("#allRoles").datagrid("getSelected");
		$.ajax({
			method:'post',
			url:'../insertRoles',
			data:{
				u_id:yhid,
				r_id:nodes.r_id
			}, 
			dataType:'json',
			success:function(res){
				if(res>0){
					$("#allUserRoles").datagrid("reload");
				}
			}
		})
	}
	function removediaUserRoles(){
		var nodes = $("#allUserRoles").datagrid("getSelected");
		$.ajax({
			method:'post',
			url:'../deleteRoles',
			data:{
				u_id:yhid,
				r_id:nodes.r_id
			}, 
			dataType:'json',
			success:function(res){
				if(res>0){
					$("#allUserRoles").datagrid("reload");
				}
			}
		})
	}
</script>
<table name="center" class="easyui-datagrid" id="dg" title="员工数据" style="height: 545px" >
	<thead>
		<tr>
			<th data-options="field:'u_id',width:80,hidden:true">用户ID</th>
			<th data-options="field:'u_name',width:100">用户名</th>
			<th data-options="field:'u_protectEmail',width:100">邮箱</th>
			<th data-options="field:'u_protectMtel',width:100,">手机号</th>
			<th data-options="field:'u_isLockout',width:100,">是否锁定</th>
			<th data-options="field:'u_createTime',width:160,">创建时间</th>
			<th data-options="field:'u_lastLoginTime',width:160,">最后登录的时间</th>
			<th data-options="field:'setRoleAction',width:60,align:'center',formatter:formatterSetRole">角色</th>
			<th data-options="field:'setUserAction',width:120,align:'center',formatter:formatterOPUser">操作</th>
			<th data-options="field:'setPassword',width:80,align:'center',formatter:formatterResetPassword">操作</th>
			<th data-options="field:'setLock',width:140,align:'center',formatter:formatterLockUser">用户操作</th>
		</tr>
	</thead>
</table>

<div id="usertb" style="padding:5px; height:auto">
	<div style="margin-bottom:5px">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addInfo()">新增</a>
		&nbsp;	用户名: <input class="easyui-textbox" id="adduserName" style="width:80px"> &nbsp;&nbsp;
				创建时间: <input class="easyui-datebox" id="beginCreateTime" style="width:100px">
				-
				<input class="easyui-datebox" id="endCreateTime" style="width:100px"> &nbsp;&nbsp; 
				
				登录时间: <input class="easyui-datebox" id="beginlastLoginTime" style="width:100px">
				-
				<input class="easyui-datebox" id="endlastLoginTime" style="width:100px">&nbsp;&nbsp;
				是否锁定:
				<select id="cc" class="easyui-combobox" name="dept" style="width:auto;">
					<option value="">--请选择---</option>
					<option value="1">是</option>
					<option value="0">否</option>
			    </select>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="searchUserInfo()">检索</a>
	</div>
</div>

<!--表单-->
<div id="adduser_window" class="easyui-window"  title="添加用户" data-options="modal:true,closed:true,iconCls:'icon-save'" style="padding:10px; top: 200px;">
	<form id="adduserForm" style="">
			<table cellpadding="5">
				<tr>
					<td>用户名:</td>
					<td><input class="easyui-textbox" type="text" name="u_name" id="u_name" data-options="required:true"></input>
						<input type="hidden" id="u_id" name="u_id" />
					</td>
				</tr>
				<!-- <tr>
					<td>密码:</td>
					<td><input class="easyui-textbox" type="password" id="u_pwd" name="u_pwd" data-options="required:true"></input>
					</td>
				</tr> -->
				<tr>
					<td>Email:</td>
					<td><input class="easyui-textbox" type="text" name="u_protectEmail" id="u_protectEmail" data-options="required:true,validType:'email'"></input>
					</td>
				</tr>
				<tr>
					<td>手机号:</td>
					<td><input type="text" class="easyui-numberbox" id="u_protectMtel" name="u_protectMtel" data-options="required:true"></td>
				</tr>
			</table>
	</form>
	<div style="text-align:center;padding:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" type="button" onclick="submitUserForms()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearUserForm()">取消</a>
	</div>
</div>

<div id="diaUserRoles" class="easyui-window" data-options="modal:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,closed:true" style="width: auto;height: auto;top: 30px;">
	<table style="margin-top: 20px; margin-left: 20px; margin-right: 20px;">
		<tr>
			<td>
				<table  id="allRoles" class="easyui-datagrid"  title="系统所有角色" data-options="rownumbers:true,singleSelect:true,method:'post'" style="width: 150px;">
					<thead>
						<tr>
							<th data-options="field:'r_id',width:80,hidden:true">ID</th>
							<th data-options="field:'r_name',width:100">名称</th>
						</tr>
					</thead>
				</table>
			</td>
			<td>
				<a href="javascript:void(0)" onclick="adddiaUserRoles()" class="easyui-linkbutton" style="width: 40px;">>></a>
				<br />
				<br />
				<a href="javascript:void(0)" onclick="removediaUserRoles()" class="easyui-linkbutton" style="width: 40px;" ><<</a>
			</td>
			<td>
				<table  id="allUserRoles" class="easyui-datagrid"  title="当前用户的角色" data-options="rownumbers:true,singleSelect:true,method:'post'" style="width: 150px;">
					<thead>
						<tr>
							<th data-options="field:'r_id',width:80,hidden:true">ID</th>
							<th data-options="field:'r_name',width:100">名称</th>
						</tr>
					</thead>
				</table>
			</td>
			<td></td>
		</tr>
	</table>
</div>