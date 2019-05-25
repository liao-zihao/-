<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myFeeling.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<p>我的日记</p>
    <table>
    	<tr>
    		<td>标题</td>
    		<td>${feeling.f_title }</td>
    	</tr>
    	<tr>
    		<td>内容</td>
    		<td>${feeling.f_content }</td>
    	</tr>
    	<tr>
    		<td>类别</td>
    		<td><c:choose>
    			<c:when test="${feeling.f_type==1 }">杂七杂八</c:when>
    			<c:when test="${feeling.f_type==2 }">有感而发</c:when>
    			<c:otherwise>踩脚印</c:otherwise>
    		</c:choose></td>
    	</tr>
    	<tr>
    		<td>日期</td>
    		<td>${fn:substring(feeling.f_date,0,10) }</td>
    	</tr>
    	<tr><td><a href="doshowall"><input type="button" value="返回"></a></td></tr>
    </table>
  </body>
</html>
