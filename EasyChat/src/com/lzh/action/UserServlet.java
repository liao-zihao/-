package com.lzh.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lzh.service.UserService;
import com.lzh.util.BaseServlet;
import com.lzh.pojo.User;

public class UserServlet extends BaseServlet {
	public String login(HttpServletRequest req,HttpServletResponse resp) {
		
		Map<String ,String[]> map=req.getParameterMap();
		User user = new User();
		
		try {
			user.setUsername(map.get("username")[0]);
			user.setPassword(map.get("password")[0]);
			UserService us = new UserService();
			User existUser = us.login(user);
			if(existUser==null) {
				req.setAttribute("msg", "用户名或密码错误");
				return "/index.jsp";
			}else {
				req.getSession().invalidate();
				
				Map<User,HttpSession> userMap = (Map<User,HttpSession>) getServletContext().getAttribute("userMap");
				
				if(userMap.containsKey(existUser)) {
					HttpSession session = userMap.get(existUser);
					session.invalidate();
				}
				
				req.getSession().setAttribute("existUser", existUser);
				ServletContext application=getServletContext();
				
				String sourceMessage="";
				
				if(null!=application.getAttribute("message")) {
					sourceMessage=application.getAttribute("message").toString();
				}
				sourceMessage += "系统公告：<font color='gray'>"
						+ existUser.getUsername() + "走进了聊天室！</font><br>";
				application.setAttribute("message", sourceMessage);
				
				resp.sendRedirect(req.getContextPath()+"/main.jsp");
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String check(HttpServletRequest req,HttpServletResponse resp) throws IOException{

		User existUser = (User)req.getSession().getAttribute("existUser");
		
		if(existUser==null) {
			resp.getWriter().println("1");
		}else {
			resp.getWriter().println("2");
		}
		
		return null;
	}
	
	public String getMessage(HttpServletRequest req,HttpServletResponse resp)throws IOException{
		
		String message=(String)getServletContext().getAttribute("message");
		if(message!=null) {
			resp.getWriter().println(message);
		}
		return null;
		
	}

	public String sendMessage(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		
		System.out.println("sendMessage invoke...");
		
		String from=req.getParameter("from");
		String to =req.getParameter("to");
		String content = req.getParameter("content");
		String face = req.getParameter("face");
		String color = req.getParameter("color");
		String sendTime = new SimpleDateFormat().format(new Date());
		
		ServletContext application=getServletContext();
		String sourceMessage=(String) application.getAttribute("message");
		sourceMessage += "<font color='blue'><strong>" + from
				+ "</strong></font><font color='#CC0000'>" + face
				+ "</font>对<font color='green'>[" + to + "]</font>说："
				+ "<font color='" + color + "'>" + content + "</font>（"
				+ sendTime + "）<br>";
		
		application.setAttribute("message", sourceMessage);
		return getMessage(req, resp);
	}

	public String exit(HttpServletRequest req,HttpServletResponse resp)throws IOException{
		HttpSession session=req.getSession();
		session.invalidate();
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
		return null;
	}

	public String kick(HttpServletRequest req,HttpServletResponse resp)throws IOException{
		int id=Integer.parseInt(req.getParameter("id"));
		Map<User,HttpSession> userMap=(Map<User,HttpSession>)getServletContext().getAttribute("userMap");
		User user=new User();
		user.setId(id);
		HttpSession session = userMap.get(user);
		session.invalidate();
		resp.sendRedirect(req.getContextPath()+"/main.jsp");
		
		return null;
	}
}
