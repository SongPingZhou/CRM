<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<script src="../js/global.js"></script>
<script type="text/javascript">
	$(function() {
		selectroles();
	});
	function selectroles(){
		$("#rolesDg").datagrid({
			url: "../selectAllRoles",
			queryParams: { //要发送的参数列表
				r_name:$("#rname").textbox("getValue")
			}
		});
	}
 	/* 打开添加窗口 */
	function addRoles() {
		$("#updateRoleForm").form("clear");
		$("#updateRole_window").window({title:'添加角色'});
		$("#updateRole_window").window("open");
	}

	function formatterOPUser(value, row, index) {
		return "<a style='cursor: pointer;' onclick='updateRoleInfo(" + index + ")'>编辑</a> <a style='cursor: pointer;' onclick='deleteRoleInfo(" + index + ")'>删除</a>";
	}
	function updateRoleInfo(index){
		//将当前数据填入表单
		$("#updateRole_window").window({title:'修改角色'});
		var data=$("#rolesDg").datagrid("getData");
		var row=data.rows[index];
		$("#updateRole_window").form("load",row);
		$("#updateRole_window").window("open");
	}
	function updatesubmitRoleForms(){
		if($("#updateRoleForm").form("validate")){
			
			$.ajax({
				type:'post',
				url:'../selectByName',
				dataType:'json',
				data:{
					r_name:$("#r_name").textbox("getValue")
				},
				success:function (res){
					if(res.success){
						$.ajax({
							type:"post",
							url:"../addEditRoles",
							dataType:'json',
							data:$("#updateRoleForm").serializeArray(),
							success:function(res){
								if(res.success){
									$.messager.alert("提示信息",res.message);
									$("#updateRole_window").window("close");
									$("#rolesDg").datagrid("reload");
								}
							}
						});
					}else{
						$.messager.alert("提示信息",res.message);
					}
				}
			
			})
		}else{
			$.messager.alert("提示信息", "请完成所有验证", "info");
		}
	}
	function deleteRoleInfo(index){
		var data = $("#rolesDg").datagrid("getData");
		var row = data.rows[index];
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
		    if (r){ // 用户点击了确认按钮
		        $.ajax({
					method:'post',
					url:'../deleteRolesById',
					dataType:"json",
					data:{
						r_id:row.r_id
					},
					success:function(res){
						if(res.success){
							$("#rolesDg").datagrid("reload");
							$.messager.alert("提示信息",res.message);
						}
					}
				}) 
		    }    
		});
	}
	function formatterSetRole(value, row, index) {
		return "<a style='cursor: pointer;' onclick='showRoles(" + index + ")'>设置</a>";
	}
	var yhna;
	var uid;
	function showRoles(index){
		var nodes=$("#rolesDg").datagrid("getData");
		var row = nodes.rows[index];
			yhna=row.r_name;
			uid=row.r_id;
		$("#opentreemenu").dialog({
			title:'您正在设置--'+yhna+'--的权限',
			toolbar:'#tas'
		})
		$("#treemenu").tree({
			url: '../selectRolesModules',
			checkbox:true,
			lines: true,
			queryParams: {
				r_id:row.r_id
			}
		})
		$("#opentreemenu").window("open");
	}
	function baocun(){
		var nodes=$("#treemenu").tree("getChecked",['checked','indeterminate']);
		var str="";
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].checked){
				str+=","+nodes[i].id+","+nodes[i].parentId;
			}
		}
		$.ajax({
			url:'../setupQuanXian',
			data:{"arr":str.substr(1),"r_id":uid},
			method:'post',
			dataType:'json',
			success:function(res){
				if(res.success){
					$("#opentreemenu").window("close");
					$.messager.alert("提示信息",res.message);
				}
			}
		})
	}
</script>
<table name="center" class="easyui-datagrid" id="rolesDg" title="角色列表" data-options="rownumbers:true,singleSelect:true,pagination:true,method:'post',toolbar:'#tb',pageSize:10">
	<thead>
		<tr>
			<th data-options="field:'r_id',width:80,hidden:true">角色id</th>
			<th data-options="field:'r_name',width:180">角色名称</th>
			<th data-options="field:'sss',width:60,align:'center',formatter:formatterSetRole">角色</th>
			<th data-options="field:'setRoleAction',width:120,align:'center',formatter:formatterOPUser">操作</th>
		</tr>
	</thead>
</table>

<div id="tb" style="padding: 5px; height: auto;">
	<div style="margin-bottom: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addRoles()">新增</a>
		角色名: <input class="easyui-textbox" id="rname">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="selectroles()">检索</a>
	</div>
</div>


<!--角色表单-->
<div id="updateRole_window" class="easyui-window"  data-options="modal:true,closed:true,iconCls:'icon-save'" style="padding:10px; top: 200px;">
	<form id="updateRoleForm">
		<table cellpadding="5">
			<tr>
				<td>角色名:</td>
				<td><input  type="hidden" id="r_id" name="r_id"  />
					<input class="easyui-textbox" type="text" name="r_name" id="r_name" data-options="required:true"/>
				</td>
			</tr>
		</table>
	</form>
	<div style="text-align:center;padding:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" type="button" onclick="updatesubmitRoleForms()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="updateclearRoleForm()">取消</a>
	</div>
</div>
<div id="opentreemenu" class="easyui-window" data-options="modal:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,closed:true" style="width: auto;height: auto;top: 30px;">
	<div id="moduleDiv" title="请选择模块" style="width: 300px; height: 400px; background: #eee;">
		<ul id="treemenu"></ul>
	</div>
	<div id="tas" style="margin-bottom: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="baocun()">提交</a>
	</div>
</div>
 
	

