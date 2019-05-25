$(function(){
	
	
	$("table").css({
		"border-collapse":"collapse",
	});
	$("td").css({
		"border":"1px solid gray",
		"text-align":"center",
		"padding":"10px 20px 10px 20px",
		
	});
	$("tr:even").css({
		"background-color":"lightblue",
	});
	
	$("body").css({
		"width":"600px",
		"margin":"0 auto",
		"padding-top":"10px"
	});
	$("#fenye").css({
		"text-align":"center",
	});
	$(".fenye").css({
		"padding-left":"10px",
	});
	$("#color").css({
		"color":"lightorange",
	});
	$("h2").css({
		"text-align":"center",
	});
	
	
	$(".fenye").unbind("click").bind("click",function(){
		var curr = $(this).html();
		
		$.ajax({
			url:"doIndex",
			type:"post",
			data:"curr="+curr,
			dataType:"html",
			async:true,
			success:function(result){
				$("table").html(result);
			},
			
		});
	});
	
	$(".del").unbind("click").bind("click",function(){
		var b_id=$(this).siblings("input").val();
		var result=window.confirm("你真的确定要删除吗？请确认");
		if(result){
			window.location="doDelbook?b_id="+b_id;
		}
	});

});