package com.lzh.dao.impl;

import java.lang.management.RuntimeMXBean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lzh.dao.StudentDao;
import com.lzh.domain.Student;
import com.lzh.util.JDBCUtil02;
import com.lzh.util.TextUtils;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

import jdk.nashorn.internal.scripts.JD;

public class StudentDaoImpl implements StudentDao{
	public List<Student> findAll()throws SQLException{
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		return runner.query("select * from stu", new BeanListHandler<Student>(Student.class));
	}

	public void insert(Student student) throws SQLException {
		System.out.println("来到insert");
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		runner.update("insert into stu values(null,?,?,?,?,?,?)",
			student.getSname(),
			student.getGender(),
			student.getPhone(),
			student.getHobby(),
			student.getInfo(),
			student.getBirthday());
	}

	@Override
	public void delete(int sid) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		runner.update("delete frome stu where sid=?",sid);
		
	}

	@Override
	public List<Student> findStudentByPage(int currentPage) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		
		return runner.query("select * from stu limit ?", new BeanListHandler<Student>(Student.class),PAGE_SIZE,(currentPage-1)*PAGE_SIZE);
	}

	@Override
	public Student findStudentById(int sid) throws SQLException {
		QueryRunner runner =new QueryRunner(JDBCUtil02.getDataSource());
		return runner.query("select * from stu where sid =?", new BeanHandler<Student>(Student.class),sid);
	}

	@Override
	public List<Student> searchStudent(String sname, String sgender) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		
		String sql = "select *from stu where 1=1";
		List<String> list = new ArrayList<String>();
		
		if(!TextUtils.isEmpty(sname)) {
			sql = sql +" and sname like ?";
			list.add("%"+sname+"%");
		}
		
		if(!TextUtils.isEmpty(sgender)) {
			sql = sql+" and gender =?";
			list.add(sgender);
		}
		return runner.query(sql, new BeanListHandler<Student>(Student.class),list.toArray());
	}

	@Override
	public void update(Student student) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		
		runner.update("update stu set sname=? , gender=? , phone=? , birthday=? , hobby=? , info=? where sid = ?", 
				student.getSname(),
				student.getGender(),
				student.getPhone(),
				student.getBirthday(),
				student.getHobby(),
				student.getInfo(),
				student.getSid());
	}

	@Override
	public int findCount() throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		
		Long result = (Long)runner.query("select count(*) from stu", new ScalarHandler());
		return result.intValue();
	}
}
