<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<script src="../js/global.js"></script>
<script type="text/javascript">
	$(function() {
		$("#treemenu").tree({
			url:'../selectModules',
			method:'post',
			lines: true,
			 onContextMenu:function(e,node){
				e.preventDefault();
				// 查找节点
				$('#treemenu').tree('select', node.target);
				// 显示快捷菜单
				$('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
			}  
		})
	});
	function myTree(){
		$("#treemenu").tree({
			url: '../selectModules',
			lines: true
		})
	}
	function addModuleInfo() {
		$("#addModuleForm").form("clear")
		var nodes = $('#treemenu').tree('getSelected'); // get checked nodes
		alert(nodes.id);
		if(nodes != null) {
			$("#parn_id").show();
			$("#parentModulename").text(nodes.text);
			$("#m_parentId").val(nodes.id);
			$("#addModule_window").window("open");
		} else {
			$.messager.alert("提示信息","请选择父节点！","info");
		}
	}
	function submitModuleForms(){
		if($("#addModuleForm").form("validate")){
			var nodes = $('#treemenu').tree('getSelected');
			$.ajax({
				url:'../addEditModules',
				method:'post',
				data:$("#addModuleForm").serializeArray(),
				dataType:'json',
				success:function(res){
					if(res.success){
						$.messager.alert("提示信息",res.message,"info");
						$("#addModule_window").window("close");
						myTree();
					}
				}
			})
		}else
		$.messager.alert("错误信息","请填写完整！","info");
	}
	function clearModuleForm(){
		$("#addModule_window").window("close");
		$("#updataModule_window").window("close");
	}
	var parentId;
	/* 打开修改窗口 */
	function updataModuledg(){
		$("#addModuleForm").form("clear")
		var nodes = $('#treemenu').tree('getSelected');
		if(nodes != null) {
			$.ajax({
				type:"post",
				dataType:'json',
				url:"../selectModulesByid",
				data:{
					m_id:nodes.id
				},
				success:function(res){
					if(res.success){
						var str=eval("("+res.message+")");
						$("#parentModulename").text(nodes.text);
						$("#addModule_window").form("load",str);  
						$("#parn_id").hide();
						$("#addModule_window").window({title:'修改窗口'});
						$("#addModule_window").window("open");
					}
				}
			});
			
		} else {
			$.messager.alert("提示信息","请选择父节点！","info");
		}
	}
	function deleteModuledg(){
		var nodes = $('#treemenu').tree('getSelected'); // get checked nodes
		if(nodes != null) {
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){ // 用户点击了确认按钮
		    	$.ajax({
		    		type:"post",
		    		url:"../deleteModules",
		    		data:{
		    			m_id:nodes.id
		    		},
		    		dataType:'json',
		    		success:function(res){
		    			if(res.success){
		    				$.messager.alert("提示信息",res.message);
		    				myTree();
		    			}
		    			
		    		}
		    	});
		    }
		 });
		} else {
			$.messager.alert("提示信息","请选择父节点！","info");
		}
	}
</script>

<table name="center1" class="easyui-datagrid" id="Moduledg" title="模块信息" style="width: 100%; height:auto;" data-options="method:'post'">
</table>
<div style="margin-bottom: 5px;">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="addModuleInfo()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="updataModuledg()">编辑</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" onclick="deleteModuledg()">删除</a>
</div>

<div id="moduleDiv" title="请选择模块" style="width: 650px; height: 500px; background: #eee;">
	<!-- <ul id="treemenu"></ul> -->
	<div id="treemenu" ><!--这个地方显示树状结构-->
	</div>
</div>

<!--新增模块-->
<div id="addModule_window" class="easyui-window" title="新增模块信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="padding:10px; top: 200px;">
	<form id="addModuleForm">
		<table cellpadding="5">
			<tr id="parn_id"> 
				<td>父节点名称:</td>
				<td> <div id="parentModulename"></div>
					<input type="hidden" id="m_id" name="m_id"/>
					<input type="hidden" id="m_parentId" name="m_parentId"/>
				</td>
			</tr>
			<tr>
				<td>权重</td>
				<td><input class="easyui-numberbox" type="text" id="weight"  name="m_weight" data-options="required:true"></input>
				</td>
			</tr>
			<tr>
				<td>URL:</td>
				<td><input class="easyui-textbox" type="text" name="m_path" id="path" data-options="required:true"></input>
				</td>
			</tr>

			<tr>
				<td>模块名称:</td>
				<td><input type="text" class="easyui-textbox" id="name" name="m_name" data-options="required:true"></td>
			</tr>
		</table>
	</form>
	<div style="text-align:center;padding:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" type="button" onclick="submitModuleForms()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearModuleForm()">取消</a>
	</div>
</div>

<div id="mm" class="easyui-menu" style="width:120px;">
	<div onclick="addModuleInfo()" data-options="iconCls:'icon-add'">追加</div>
	<div onclick="updataModuledg()" data-options="iconCls:'icon-edit'">编辑</div>
	<div onclick="deleteModuledg()" data-options="iconCls:'icon-remove'">移除</div>
</div>
