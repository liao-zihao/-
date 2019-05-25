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
    
    <title>My JSP 'showVote.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/showVote.css"/>

	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
   	<script type="text/javascript" src="js/showVote.js"></script>
   	
  </head>
  
  <body>
	<div class="showVote">
		<div class="findtitle">
			<span class="fr">
				<input type="text"/>
				<input type="button" value="搜索">
			</span>
		</div>
		<div class="showVoteT">
			<table>
				<tr>
					<td class="td1"></td>
					<td class="td2">投票标题</td>
					<td class="td3">选项数</td>
					<td class="td4">投票人数</td>
					<td class="td5">操作</td>
				</tr>
				<c:forEach var="sub" items="${showList}">
					<tr>
						<td class="td1">${sub.sid}</td>
						<td class="td2">${sub.title}</td>
						<td class="td3">${sub.numoptions}</td>
						<td class="td4">0</td>
						<td class="td5"><a class="joinVote">参加投票</a></td>
					</tr>
				</c:forEach>
				
				<tr>
			<td colspan="5">
     			<p class="ppage">
					<span>当前页数：[<span class="now">${page.currpage}</span>/<span class="last">${page.pagenum}</span>]</span>
					<a href="" class="firstPage" >首页</a>
					<a href="" class="nextPage">下一页</a>
					<a href="" class="previousPage">上一页</a>
					<a href="" class="lastPage">尾页</a>
				</p>
    			</td>
		</tr>
			</table>
		</div>
	</div>
		
  </body>
</html>
