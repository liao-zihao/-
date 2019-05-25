package com.lzh.service;

import java.sql.SQLException;
import java.util.List;

import com.lzh.domain.PageBean;
import com.lzh.domain.Student;

public interface StudentService {
	List<Student> findAll() throws SQLException;
	
	void insert(Student student)throws SQLException;
	
	void delete(int sid) throws SQLException;
	
	PageBean findStudentByPage(int currentPage) throws SQLException;
	
	Student findStudentById(int sid)throws SQLException;
	
	List<Student> searchStudent(String sname,String sgender)throws SQLException;

	void update(Student student)throws SQLException;
}

