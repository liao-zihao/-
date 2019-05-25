package com.lzh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzh.domain.Student;
import com.lzh.service.StudentService;
import com.lzh.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			int sid = Integer.parseInt(request.getParameter("sid"));
			String sname = request.getParameter("sname"); //sname:zhangsan
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String birthday = request.getParameter("birthday"); // 1989-10-18
			String info = request.getParameter("info");
			
			String[] h = request.getParameterValues("hobby");
			//		[篮球，足球，写字] --- 篮球，足球，写字
			String hobby = Arrays.toString(h);
			hobby = hobby.substring(1, hobby.length() - 1);
			//2. 添加到数据库
			//string -- date
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
			Student student = new Student(sid ,sname, gender, phone, hobby, info, date);
			
			//2. 更新数据库数据
			StudentService service = new StudentServiceImpl();
			service.update(student);
			
			request.getRequestDispatcher("StudentListServlet").forward(request, response);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
