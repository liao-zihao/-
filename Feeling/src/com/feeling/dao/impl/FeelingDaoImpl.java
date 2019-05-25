package com.feeling.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.feeling.bean.Feeling;
import com.feeling.bean.Page;
import com.feeling.dao.FeelingDao;
/**
 * 日记数据访问实现类
 * @author hasee
 * @version LJ 2017-10-21
 */
public class FeelingDaoImpl implements FeelingDao{
	String path="MyXml.xml";
	
	/**
	 * 查询所有方法
	 */
	public List<Feeling> showAll(Page p) {
		Reader reader =null;
		SqlSession session=null;
		List<Feeling> list =new ArrayList<Feeling>();
		try {
			reader =Resources.getResourceAsReader(path);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factory=builder.build(reader);
			session=factory.openSession();
			list=session.selectList("com.feeling.dao.FeelingDao.showAll", p);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 查询行数方法
	 */
	public int searchAllCount() {
		Reader reader =null;
		SqlSession session=null;
		int result=0;
		try {
			reader =Resources.getResourceAsReader(path);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factory=builder.build(reader);
			session=factory.openSession();
			result=session.selectOne("com.feeling.dao.FeelingDao.searchAllCount");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 添加日记方法
	 */
	public int addFeeling(Feeling feeling) {
		Reader reader =null;
		SqlSession session=null;
		int result=0;
		try {
			reader =Resources.getResourceAsReader(path);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factory=builder.build(reader);
			session=factory.openSession();
			result=session.insert("com.feeling.dao.FeelingDao.addFeeling",feeling);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally{
			session.close();
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 查询单条日记方法
	 */
	public Feeling searchFeeling(int id) {
		Reader reader =null;
		SqlSession session=null;
		Feeling feeling=new Feeling();
		try {
			reader =Resources.getResourceAsReader(path);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factory=builder.build(reader);
			session=factory.openSession();
			feeling=session.selectOne("com.feeling.dao.FeelingDao.searchFeeling", id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return feeling;
	}
	
}
