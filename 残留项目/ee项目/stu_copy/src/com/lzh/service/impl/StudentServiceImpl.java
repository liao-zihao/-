package com.lzh.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.lzh.dao.StudentDao;
import com.lzh.dao.impl.StudentDaoImpl;
import com.lzh.domain.PageBean;
import com.lzh.domain.Student;
import com.lzh.service.StudentService;

public class StudentServiceImpl implements StudentService{
	public List<Student> findAll() throws SQLException{
		StudentDao dao = new StudentDaoImpl();
		return dao.findAll();
	}
	
	public void insert(Student student)throws SQLException{
		StudentDao dao = new StudentDaoImpl();
		dao.insert(student);
	}

	@Override
	public void delete(int sid) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		dao.delete(sid);
	}

	

	@Override
	public Student findStudentById(int sid) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		return dao.findStudentById(sid);
	}

	@Override
	public List<Student> searchStudent(String sname, String sgender) throws SQLException {
		return new StudentDaoImpl().searchStudent(sname, sgender);
	}

	@Override
	public void update(Student student) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		dao.update(student);
		
	}
	
	@Override
	public PageBean findStudentByPage(int currentPage) throws SQLException {
		PageBean<Student> pageBean = new PageBean<Student>();
		
		int pageSize = StudentDao.PAGE_SIZE;
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		
		StudentDao dao = new StudentDaoImpl();
		List<Student> list = dao.findStudentByPage(currentPage);
		pageBean.setList(list);
		
		int count = dao.findCount();
		pageBean.setTotalSize(count);
		pageBean.setTotalPage(count%pageSize==0?count/pageSize: (count/pageSize)+1);
		return pageBean;
		
	}

}
