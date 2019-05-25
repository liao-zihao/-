<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src = "js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src = "js/SimpleTree.js"></script>
	<script type="text/javascript" >
		$(function(){
			$(".st_tree").SimpleTree({
		        click:function(a){
		            if(!$(a).attr("hasChild"))
		                alert($(a).attr("ref"));
		        }
	    	});
	    	$(".st_tree").css({
				"display":"block-inline",
				"float":"left",
				"width":"200px"
			});
		});
			
	</script>
  </head>
  
  <body>
    <div class="st_tree">
		<ul>
		    <li><a href="#" ref="hyjm">功能菜单</a></li>
		    <ul>
		    	<li><a href="#" ref="xtgl">系统管理</a></li>
		    	<li><a href="#" ref="xtgl">用户管理</a></li>
			    <ul show="true">
			        <li><a href="#" ref="yhgl">个人信息</a></li>
			        <li><a href="#" ref="rzck">投票记录</a></li>
			    </ul>
			    <li><a href="#" ref="xtgl">投票管理</a></li>
			    <ul>
			        <li><a href="#" ref="kcgl">维护</a></li>
			        <li><a href="#" ref="shgl">发布新投票</a></li>
			        <li><a href="#" ref="fhgl">所有投票</a></li>
			    </ul>
		    </ul>
		    
		</ul>
	</div>
  </body>
</html>
