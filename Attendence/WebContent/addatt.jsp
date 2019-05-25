<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addatt.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/addatt.js"></script>
  </head>
  
  <body>
  <form action="doaddatt" method="post">
    <table>
    	<tr><td colspan="3">考勤记录登记表</td></tr>
    	<tr>
	    	<td>姓名</td>
	    	<td><input type="text" name="aname"></td>
	    	<td></td>
    	</tr>
    	<tr>
	    	<td>部门</td>
	    	<td><input type="text" name="dept"></td>
	    	<td></td>
    	</tr>
    	<tr>
	    	<td>考勤日期</td>
	    	<td><input type="text" name="adate"></td>
	    	<td>日期格式：yyyy-MM-dd</td>
    	</tr>
    	<tr>
	    	<td>考勤状态：</td>
	    	<td>
	    		<select name="status">
	    			<option>正常</option>
	    			<option>迟到</option>
	    			<option>早退</option>
	    			<option>外出</option>
	    			<option>休假</option>
	    		</select>
	    	</td>
	    	<td></td>
    	</tr>
    	<tr><td colspan="3">
    		<input type="submit" value="登记">
    		<input type="reset">
    	</td></tr>
    </table>
    </form>
    
  </body>
</html>
