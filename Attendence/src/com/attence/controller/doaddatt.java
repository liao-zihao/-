package com.attence.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attence.bean.Attence;
import com.attence.service.AttenceService;
import com.attence.service.impl.AttenceServiceImpl;

public class doaddatt extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doaddatt() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Attence attence=new Attence();
		attence.setEmpname(request.getParameter("aname"));
		attence.setChkdate(request.getParameter("adate"));
		attence.setDept(request.getParameter("dept"));
		attence.setStatus(request.getParameter("status"));
		
		AttenceService as=new AttenceServiceImpl();
		int result=as.addatt(attence);
		
		if(result>0){
			response.sendRedirect("blank.jsp");
		}else{
			request.setAttribute("mess", "登记失败");
			request.getRequestDispatcher("doaddatt").forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
