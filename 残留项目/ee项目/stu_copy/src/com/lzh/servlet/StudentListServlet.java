package com.lzh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzh.domain.Student;
import com.lzh.service.StudentService;
import com.lzh.service.impl.StudentServiceImpl;

public class StudentListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			StudentService service = new StudentServiceImpl();
			List<Student> list = service.findAll();
			
			request.setAttribute("list",list);
			request.getRequestDispatcher("list.jsp").forward(request, response);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
