$(function(){

	$(".st_tree").css({
		"display":"block-inline",
		"float":"left",
		"width":"200px"
	});
	
	
	$("form").submit("click",function(){
		var uname = $("input[name=uname]").val();
		var uid = $("input[name=uid]").val();
		var pwd = $("input[name=pwd]").val();
		var pwdagain = $("input[name=pwdagain]").val();
		
		if(uname==""){
			alert("用户名必须录入");
			return false;
		}else if(uid==""){
			alert("用户id必须录入");
			return false;
		}else if(pwd==""){
			alert("用户密码必须录入");
			return false;
		}else if(pwd!=pwdagain){
			alert("密码不一致");
			return false;
		}
		
	});
	
});