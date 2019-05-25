<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addSubject.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/addSubject.css"/>
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
   	<script type="text/javascript" src="js/addSubject.js"></script>
  </head>
  
  <body>
    <div class="add">
		<div class="addtitle">添加新的投票</div>
		<div class="addcontent">
			<form action="addSubject.action" method="post">
				<table>
					<tr>
						<td>投票内容：</td>
						<td><input type="text" name="title" class="inputtext"/></td>
					</tr>
					<tr>
						<td>投票类型：</td>
						<td>
							<input type="radio" name="type" value="1" checked="checked"/>单选
							<input type="radio" name="type" value="2"/>多选
						</td>
					</tr>
					<tr>
						<td>投票选项：</td>
						<td><input type="text" name="option" class="inputoption"/></td>
					</tr>
				</table>
				<input type="submit" value="确定"/>
				<input type="button" class="addOption" value="增加选项"/>
				<input type="button" class="backShowSubject" value="取消操作"/>
			</form>
		</div>
	</div>
  </body>
</html>
