<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script src="js/global.js"></script> 
		<script src="jquery-easyui-1.4.3/jquery.cookie.js"></script> 
		<script>
		//树形结构显示
	    $(function(){
	    	var roleName=globalData.getCurUName();
	    	$("#spName").text(globalData.getCurUName());
	    	
	    	$("#menuTree").tree({
	    		url:'moduls?u_id='+<%=request.getSession().getAttribute("u_id")%>,
	    		method:'post',
	    		lines: true,
	    		onClick: function (node){
	    			 var flag = $("#tt").tabs('exists', node.text);
	    			 var isLeaf = $('#treeUlId').tree('isLeaf',node.target); //是否是叶子节点
	                 if (isLeaf) {//只有叶子节点才会在选项卡中创建选项页（每个选项页对应1个功能）
	                     if(!flag) {
	                         $('#tt').tabs('add', { //在选项卡中，创建1个选项页
	                             title: node.text,   //选项卡中，选项页的标题（在同一个选项卡中，选项页需要保持一致）。
	                             closable: true,
	                             content: "<iframe  src='"+node.url+"' style='height:100%;width:100%'/>"    //此处做了调整，推荐使用iframe的方式实现
	                        });
	                     } else {
	                         $("#tt").tabs('select', node.text); //直接选中title对应的选项卡
	                     }
	                 }
	    		}
	    		
	    	});
	    });  
    function tuichu(){
    	$.messager.confirm("确认","你确认要推出当前页面吗？",function(r){
    		if(r){
    			$.removeCookie('u_pwd',{ path: '/'});
    			$.removeCookie('u_name',{ path: '/'});
	    		sessionStorage.clear();
	    		window.location.href="index.jsp";
    		}
    	});
    }
		</script>
	</head>
	<body> 
		<div style="margin:20px 0;"></div>
	    <div class="easyui-layout" style="width:100%;height:700px;">
	        <div data-options="region:'north'" style="height:50px">
	        	CRM系统&nbsp;&nbsp;欢迎您：<span id="spName"></span>&nbsp;&nbsp;
	        	<a id="btn" href="javascript:void" onclick="tuichu()" style="cursor:pointer">安全退出</a>
	        </div>
	        <div data-options="region:'south',split:true" style="height:50px;"></div>
	        <div daata-options="region:'east',split:true,collapsed:true,title:'East'" style="width:150px;padding:10px;">东部区域</div>
		        <div data-options="region:'west',split:true" title="导航应用" style="width:150px;">
		           <div id="menuTree" ><!--这个地方显示树状结构-->
		           		
		           </div>
		        </div>
	        
	        <div id="center_1" data-options="region:'center',iconCls:'icon-ok'"">
	            <div id="tt" class="easyui-tabs" data-options="fit:true"> <!--这个地方采用tabs控件进行布局-->
	             	
	            </div>
	        </div>
	    </div>
	</body>
</html>
