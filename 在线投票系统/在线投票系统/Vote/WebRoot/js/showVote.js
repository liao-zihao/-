$(function(){
	$(".showVoteT tr:even").css("background-color","#ccc");
	$(".issue").unbind("click").bind("click",function(){
		$.ajax({
			url:"addSubjectHtml.action",
			type:"post",
			dateType:"html",
			async:true,
			success:function(result){
				$(".show").html(result);
			}
		});
	});
	
	$(".firstPage").unbind("click").bind("click",function(){
		$.ajax({
			url:"doindex.action",
			type:"post",
			data:{
				"yema":1
				},
			dateType:"html",
			async:true,
			success:function(result){
				$(".show").html(result);
			}
			
		});
		return false;
	});

	$(".lastPage").unbind("click").bind("click",function(){
		var lastyema = $(".last").html();
	
		$.ajax({
			url:"doindex.action",
			type:"post",
			data:{
				"yema":lastyema
				},
			dateType:"html",
			async:true,
			success:function(result){
				$(".show").html(result);
			}
			
		});
		return false;
	});
	$(".nextPage").unbind("click").bind("click",function(){
		var nowyema = $(".now").html();
		var lastyema = $(".last").html();
		if(nowyema==lastyema){
			alert("已经是最后一页了");
		}else{
			$.ajax({
				url:"doindex.action",
				type:"post",
				data:{
					"yema":parseInt(nowyema)+1
					},
				dateType:"html",
				async:true,
				success:function(result){
					$(".show").html(result);
					
				}
				
			});	
		}	
		return false;
	});
	
	$(".previousPage").unbind("click").bind("click",function(){
		var nowyema = $(".now").html();
		if(nowyema==1){
			alert("已经是第一页了");
		}else{
			$.ajax({
				url:"doindex.action",
				type:"post",
				data:{
					"yema":parseInt(nowyema)-1,
				},
				dateType:"html",
				async:true,
				success:function(result){
					$(".show").html(result);
					
				}
				
			});	
		}	
		return false;
	});
	
	$(".find").unbind("click").bind("click",function(){
		var title = $(".findtext").val();
		$.ajax({
			url:"showSubject.action",
			type:"post",
			data:{
				"title":title,
				"yema":1,
			},
			dateType:"html",
			async:true,
			success:function(result){
				$(".show").html(result);
				
			}
		});	
	});
});