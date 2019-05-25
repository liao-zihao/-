$(function(){
	$("form").submit(function(){
		var title=$("input[name=title]").val();
		var content=$("#content").val();
		var type=$("#type").val();
		alert(type);
		if(title==""){
			alert("标题不能为空");
			return false;
		}else if(content==""){
			alert("内容不能为空");
			return false;
		}else if(type=="-请选择-"){
			alert("请选择类型");
			return false;
		}
		
	});
	$("td").css({
		"padding-top":"5px"
	});
	
});