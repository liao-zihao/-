$(function(){
	$("td").css({
		"padding-left":"10px",
		"padding-right":"10px",
		"text-align":"center",
		"border":"1px solid gray",
	});
	$("table").css({
		"border-collapse":"collapse",
		"margin-top":"10px",
	});
	$("tr:first").css({
		"background-color":"lightgray",	
	});
	
	
	$("form").submit(function(){
		var a=$("input").val();
		if(a==""){
			alert("输入不能为空");
			return false;
		}
		var reg=/^[0-9]{4}\-[0-9]{1}[1-9]{1}\-[0-9]{1}[1-9]{1}$/;
		if(!reg.test($("input[name=adate]").val())){
			alert("日期格式不正确");
			return false;
		}
	});
	
});