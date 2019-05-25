$(function(){
	
	$("body").css({
		"margin":"0 auto",
		"width":"400px",
		"margin-top":"20px",
	});
	$("td").css({
		"padding-right":"10px",
		"padding-bottom":"10px",
		"text-align":"center",
		"border":"1px solid gray"
	});
	$("table").css({
		"border-collapse":"collapse",
		"margin-top":"10px",
	});
	$("tr:even").css({
		"backgroung-color":"lightgray"
	});
	
	
	$(".fenye").unbind("click").bind("click",function(){
		
		var curr = $(this).html();
		$.ajax({
			url:"doshowall",
			type:"post",
			data:"curr="+curr,
			dataType:"html",
			async:true,
			success:function(result){
				$("table").html(result);
			},
			
		});
	});
	
});