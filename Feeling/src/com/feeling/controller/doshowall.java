package com.feeling.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feeling.bean.Feeling;
import com.feeling.bean.Page;
import com.feeling.service.FeelingService;
import com.feeling.service.impl.FeelingServiceImpl;

public class doshowall extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doshowall() {
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
    	Page p = new Page();
    	p.setRownum(3);
    	p.setAllrownum(fs.searchAllCount());
    	
    	if(p.getAllrownum()%p.getRownum()==0){
    		p.setPagenum(p.getAllrownum()/p.getRownum());
    	}else{
    		p.setPagenum(p.getAllrownum()/p.getRownum()+1);
    	}
    	String curr = request.getParameter("curr");
    	
    	if(curr ==null){
    		p.setCurrpage(1);
    		p.setStart((p.getCurrpage()-1)*p.getRownum());
    		List<Feeling> list = fs.showAll(p);
        	request.setAttribute("p", p);
        	request.setAttribute("list", list);
        	request.getRequestDispatcher("showall.jsp").forward(request, response);
    	}else{
    		p.setCurrpage(Integer.valueOf(curr));
    		p.setStart((p.getCurrpage()-1)*p.getRownum());
    		List<Feeling> list = fs.showAll(p);
        	request.setAttribute("p", p);
        	request.setAttribute("list", list);
    		request.getRequestDispatcher("divshow.jsp").forward(request, response);
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
