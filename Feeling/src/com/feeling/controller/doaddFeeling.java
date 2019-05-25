package com.feeling.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feeling.bean.Feeling;
import com.feeling.service.FeelingService;
import com.feeling.service.impl.FeelingServiceImpl;

public class doaddFeeling extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doaddFeeling() {
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
    	FeelingService fs = new FeelingServiceImpl();
    	Feeling feeling=new Feeling();
    	feeling.setF_content(request.getParameter("content"));
    	feeling.setF_title(request.getParameter("title"));
    	String flag=request.getParameter("type");
    	int type=0;
		if(flag=="杂七杂八"){
			type=1;
		}else if(flag=="踩脚印"){
			type=3;
		}else{
			type=2;
		}
		feeling.setF_type(type);
		int result=fs.addFeeling(feeling);
		if(result>0){
			request.getRequestDispatcher("blank.jsp").forward(request, response);
		}else{
			request.setAttribute("mess", "添加失败");
			request.getRequestDispatcher("addFeeling.jsp").forward(request, response);
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
