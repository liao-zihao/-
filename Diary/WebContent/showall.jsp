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
    
    <title>My JSP 'showall.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src = "js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src = "js/showall.js"></script>
  </head>
  
  <body>
    <h3>心情足迹</h3>
    <span><a href="addFeeling.jsp">记录心情</a></span>
    <table>
    	<tr>
    		<td>心情日记</td>
    		<td>日记分类</td>
    		<td>编写日期</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach var="l" items="${list }">
	    	<tr>
	    		<td>${l.f_title }</td>
	    		<td><c:choose>
	    			<c:when test="${feeling.type==1 }">杂七杂八</c:when>
	    			<c:when test="${feeling.type==2 }">有感而发</c:when>
	    			<c:otherwise>踩脚印</c:otherwise>
	    		</c:choose></td>
	    		<td>${fn:substring(l.f_date,0,10) }</td>
	    		<td><a href="searchFeeling?id=${l.f_id }">查看</a></td>
	    	</tr>
    	</c:forEach>
    </table>
    <h2>
  	 	<c:forEach begin="1" end="${p.pagenum }" step="1" varStatus="status">
  	 		<a class="fenye">${status.index }</a>
  	 	</c:forEach>
  	 </h2>	
  </body>
</html>
