package com.traininfo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.traininfo.bean.TrainInfo;
import com.traininfo.service.TrainInfoService;
import com.traininfo.service.impl.TrainInfoserviceImpl;
/**
 * 车票信息显示控制类
 * @author hasee
 * @version LJ 2017-10-9
 */
public class doindexServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doindexServlet() {
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
	
	/**
	 * 车票信息显示方法
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TrainInfoService tis=new TrainInfoserviceImpl();
		List<TrainInfo> list= tis.getTrainInfo();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}
}
