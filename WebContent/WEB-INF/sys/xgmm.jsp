<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="mdDialog" class="easyui-dialog" title="注册"   
	        data-options="iconCls:'icon-ok',resizable:true,modal:true">   
	    <form id="mdform" style="text-align: right;">  
		    <div>   
		        <label for="name">旧密码:</label>   
		        <input class="easyui-textbox" type="password" name="u_pwd" />   
		    </div>   
		    <div>   
		        <label for="email">新密码:</label>   
		        <input class="easyui-textbox" type="password" name="u_pwd" />
		    </div>
		    <div>   
		        <label for="email">确认密码:</label>   
		        <input class="easyui-textbox" type="password" name="u_pwd" />
		    </div>
		</form>
		<div id="tj" style="text-align: center; padding:3px">
			<a id="btn" href="javascript:void(0)" onclick="dlmd()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>  
			<a id="btn" href="javascript:void(0)" onclick="clearmd()" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">取消</a>  
		</div>
	</div>
</body>
</html>