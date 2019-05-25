<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showtrain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		table tr td{
			border:1px solid lightgray;
			text-align:center;
		}
		table{
			border-collapse: collapse;
		}
		#gray{
			background:gray;
		}
	</style>
  </head>
  
  <body>
    <table>
    	<tr id="gray"><td colspan="2">火车车次详情</td></tr>
    	<tr>
    		<td>车次</td>
    		<td>${trainInfo.train_no }</td>
    	</tr>
    	<tr>
    		<td>发车————到达</td>
    		<td>${trainInfo.start_station }——${trainInfo.arrival_station }</td>
    	</tr>
    	<tr>
    		<td>发时————到时</td>
    		<td>${trainInfo.start_time }———${trainInfo.arrival_time }</td>
    	</tr>
    	<tr>
    		<td>车型</td>
    		<td>${trainInfo.type }</td>
    	</tr>
    	<tr>
    		<td>运行时间（小时）</td>
    		<td>${trainInfo.runtime }</td>
    	</tr>
    	<tr>
    		<td>里程（公里）</td>
    		<td>${trainInfo.mile }</td>
    	</tr>
    </table>
  </body>
</html>
