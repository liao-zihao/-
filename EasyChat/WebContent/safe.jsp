<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%if(null==session.getAttribute("existUser")){
	out.println("<script language='javascript'>alert('账户过期，重新登录!');window.location='index.jsp';</script>");
}%>