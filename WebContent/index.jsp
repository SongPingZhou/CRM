<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script src="js/global.js"></script>
		<script src="jquery-easyui-1.4.3/jquery.cookie.js"></script>
		<script>
			$(function(){
				document.onkeydown=function(event){ 
				e = event ? event :(window.event ? window.event : null); 
					if(e.keyCode==13){ 
						submitLoginForm();
					}
				}
				/* 将验证码隐藏 */
				$("#yzm").hide();
				/* 获取cookis的值 */
				if($.cookie('u_name') !=undefined && $.cookie('u_pwd')!=undefined){
					$("#u_name").val($.cookie('u_name'));
					$("#u_pwd").val($.cookie('u_pwd'));
					// 这个事件可以模拟“我”直接触发登录的那个点击事件
					submitLoginForm();
				}
			});
			
			/* 验证用户名是否存在 */
			var y;
			function onblus(){
				var u_name=$('#u_name').val();
				$.ajax({
					url:'login',
					method:'post',
					data:{
						u_name:u_name
					},
					dataType:"json",
					success:function(res){
						if(res.success){
							
						}else{
							y=1;
							$.messager.alert('提示信息',res.message); 
							return;
						}
					}
				})
				
				/*  var u_name=$('#u_name').val();
				$("#u_name").textbox({
			    	required : true,
			    	missingMessage : '用户名不能为空 ! ',
			    	invalidMessage : '用户名不存在',
			    	validType : "remote['login','u_name']"
			    }) */

			}

			//产生不同的图片
			function changeR() {
				// 用于点击时产生不同的验证码
				$("#imgyzm").attr("src",
						"KaptchaServlet?time=" + new Date().getTime());
			}
			
			function islocking(){
				$.ajax({
					url:'locking',
					method:'post',
					data:{
						u_id:<%=request.getSession().getAttribute("u_id")%>,
						u_isLockout:"2"
					},
					dataType:'json',
					success:function(res){
						if(res>0){
							$.messager.alert("提示信息", "用户已锁定", "info");
						}
					}
				})
			}
			var num=0;
			var n1=3;
			function submitLoginForm() {
				  onblus();  
				 if(y==1){
					 y=0;
					 return;
				 }  
				if(num>=3){
					if(!$("#yzform").form("validate")){
						$.messager.alert("提示信息", "请输入验证码！", "info");
						return;
					}else{
						$("#yzm1").val($("#yzmyzm").val());
					}
				}
				if ($("#loginForm").form("validate")) {
					$.ajax({
						method : 'post',
						url :"login",
						data : $("#loginForm").serializeArray(),
						dataType : 'json',
						success : function(res) {
							if (res.success) {
								num=0;
								globalData.setUserInfo(<%=request.getSession().getAttribute("u_id")%>, $("#u_name").val());
								window.location.href = "main.jsp";
							} else{//密码错误次数大于等于三次将开启验证码
								if(res.remark==1){
									$.messager.alert("出错了", res.message, "error");
									return;
								}
								num++;
								if(num>=3){
									/* num=0; */
									$("#yzm").show();
									n1--;
									if(n1>0){
										$.messager.alert("出错了","密码错误您还有-"+n1+"-次机会,之后将锁定改用户", "error");
										return;
									}else{
										islocking();
										//$.messager.alert("出错了", "锁定", "error");
										return;
									} 
								}
								$.messager.alert("出错了", res.message, "error");
							}
						}
					});

				} else
					$.messager.alert("验证", "请完善数据", "info");
			}
		</script>
	</head>
	<body>
		<div style="margin:auto auto;width:370px;height: 200px; margin-top: 220px;">
			<div class="easyui-panel" title="登录界面" >
				<div style="padding:10px 60px 20px 60px">
					<form id="loginForm">
						<table cellpadding="5">
							<tr>
								<td>登陆名:</td>
								<td><input class="easyui-validatebox" type="text"
									onblur="onblus()" data-options="required : true" name="u_name"
									id="u_name" /></td>
							</tr>

							<tr>
								<td>密&nbsp;&nbsp;&nbsp;码:</td>
								<td><input class="easyui-validatebox"
									data-options="required : true" type="password" name="u_pwd"
									id="u_pwd" /><input type="hidden" id="yzm1" name="yzm" /></td>
									
							</tr>
						</table>
					</form>
					<form id="yzform">
						<table id="yzm" cellpadding="5">
							<tr>
								<td>验证码:</td>
								<td><input class="easyui-validatebox"
									data-options="required : true" type="text" id="yzmyzm" /></td>
							</tr>
							<tr>
								<td></td>
								<td><img alt="验证码" id="imgyzm" onclick="changeR()"
									src="KaptchaServlet"> <a href="javascript:changeR()">不会
										换一张</a></td>
							</tr>
						</table>
					</form>
				<br />
					<div style="text-align:center;padding:5px">
						<a href="javascript:void(0)" class="easyui-linkbutton" type="button" onclick="submitLoginForm()" data-options="iconCls:'icon-ok'">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" data-options="iconCls:'icon-clear'">取消</a>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>