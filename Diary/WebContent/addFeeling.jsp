<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addFeeling.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src = "js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src = "js/addFeeling.js"></script>
  </head>
  
  <body>
  	${mess }
    <form action="doaddFeeling" method="post">
    	<table>
    		<tr>
    			<td></td>
    			<td>我的日记</td>
    		</tr>
    		<tr>
    			<td>标题</td>
    			<td><input type="text" name="title"></td>
    		</tr>
    		<tr>
    			<td>内容</td>
    			<td><textarea cols="30" rows="6" id="content" name="content"></textarea></td>
    		</tr>
    		<tr>
    			<td>类别</td>
    			<td>
    				<select id="type" name="type">
    					<option>-请选择-</option>
    					<option>有感而发</option>
    					<option>杂七杂八</option>
    					<option>踩脚印</option>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td></td>
    			<td><input type="submit" value="保存"></td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
