package com.lzh.util;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet{
	protected void service(HttpServletRequest req,HttpServletResponse res) 
		throws ServletException,IOException
	{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		String methodName = req.getParameter("method");
		
		if(methodName==null||methodName.isEmpty()) {
			methodName="execute";
		}
		
		Class c=this.getClass();
		try {
			Method m = c.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//反射获取方法
			String result=(String)m.invoke(this, req,res);
			if(result!=null&&!result.isEmpty()) {
				req.getRequestDispatcher(result).forward(req, res);
			}
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}
}
