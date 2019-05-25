$(function(){
	$("tr:even").css({
		"background":"lightgray",
	});
	
	$("#del").unbind("click").bind("click",function(){
		
		var select=$(".check");
		var flag=false;
		for(var i=0;i<select.length;i++){
			if(select[i].checked){
				flag=true;
				break;
			}
		}
		if(flag==true){
			
			$("form").submit();
		}else{
			alert("请选择车次！");
		}
	});
	
});