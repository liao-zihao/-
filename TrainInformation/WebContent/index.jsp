<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<script type="text/javascript" src = "js/index.js"></script>
	<style type="text/css">
		table tr td{
			border:1px solid lightgray;
			text-align:center;
		}
		table{
			border-collapse: collapse;
		}
	</style>
  </head>
  
  <body>
    <form action="dodeleteServlet" method="post">
    	<table>
    		<tr>
    			<td id="del">删除</td>
    			<td>车次</td>
    			<td>发车————到达</td>
    			<td>发时————到时</td>
    			<td>车型</td>
    			<td>运行时间（小时）</td>
    		</tr>
    		<c:forEach var="l" items="${list }">
	    		<tr>
	    			<td><input class="check" name="check" type="checkbox" value="${l.train_no }"></td>
	    			<td class="train_no"><a href="doshowtrain?train_no=${l.train_no }">${l.train_no }</a></td>
	    			<td>${l.start_station }——${l.arrival_station }</td>
	    			<td>${l.start_time }——${l.arrival_time }</td>
	    			<td>${l.type }</td>
	    			<td>${l.runtime }</td>
	    		</tr>
    		</c:forEach>
    	</table>
    	
    </form>
    
  </body>
</html>
