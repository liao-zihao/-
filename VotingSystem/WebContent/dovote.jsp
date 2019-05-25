<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dovote.jsp' starting page</title>
    
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
  <form action="addvote.action" method="post">
    <p>${vo.title }</p>
    <p>共有${vo.numoptions }个选项，已有${vo.votenum }个网友参与了投票。
    <table>
	    <c:forEach var="l" items="${olist }">
	    	<tr>
	    		<td>${l.vo_id }</td>
	    		<td>
	    			<input type="hidden" value="${l.vs_id }" name="vs_id"/>
	    			<input type="checkbox" name="vo_option" value="${l.vo_id }"/>
	    			#{vo_option }
	    		</td>
	    	</tr>
	    </c:forEach>
    </table>
    <input type="submit" value="投票"><a href="doindex.action"><input type="button" value="查看"></a>
    </form>
  </body>
</html>
