var globalData={
	/*server:"http://stuapi.ysd3g.com/api/",*/
	server:"http://127.0.0.1:8080/SSM/",
	/*per:"http://127.0.0.1:8020/EasyUIThree/",*/
	per:"http://127.0.0.1:8080/SSM/",
	token:"5eb920d9-651f-41a6-8eaf-5f0bfbd206c4",
	myTheme:"default",
	setUserInfo:function(uid,uname){
		sessionStorage.setItem("uid",uid);
		sessionStorage.setItem("uname",uname)
		/*sessionStorage.setItem("roleNames",roleNames)*/
	},
	getCurUid:function(){
		return sessionStorage.getItem("uid");
	},
	getCurUName:function(){
		return sessionStorage.getItem("uname");
	}, 
	getCurRoleNames:function(){
		var rs= sessionStorage.getItem("roleNames");
		var arr =rs.split(",");
		var data="[";
		for(var i=0;i<arr.length;i++){
			arr[i]="\'"+arr[i]+"\'";
		}
		return eval("["+arr.join()+"]");
	}
}
document.write('<link rel="stylesheet" href="'+globalData.per+'jquery-easyui-1.4.3/themes/'+globalData.myTheme+'/easyui.css" type="text/css"/>');
document.write('<link rel="stylesheet" href="'+globalData.per+'jquery-easyui-1.4.3/themes/icon.css" type="text/css"/>');
document.write('<script type="text/javascript" src="'+globalData.per+'jquery-easyui-1.4.3/jquery.min.js" ></script>');
document.write('<script type="text/javascript" src="'+globalData.per+'jquery-easyui-1.4.3/jquery.easyui.min.js" ></script>');
document.write('<script src="'+globalData.per+'jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>');
document.write('<script src="'+globalData.per+'js/easyUIvalidate.js"></script>'); 
