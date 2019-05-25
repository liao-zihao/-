package com.vote.dao.impl;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vote.bean.Vote_user;
import com.vote.dao.Vote_userDao;

public class Vote_userDaoImpl implements Vote_userDao{
	String path="Vote.xml";
	public int searchUname(Vote_user vu) {
		
		Reader reader =null;
		SqlSession session=null;
		int result=0;
		try {
			reader =Resources.getResourceAsReader(path);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factory=builder.build(reader);
			session=factory.openSession();
			result=session.selectOne("com.vote.dao.Vote_userDao.searchUname", vu);
			
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

	public int doregister(Vote_user vu) {
		Reader reader =null;
		SqlSession session=null;
		int result=0;
		try {
			reader =Resources.getResourceAsReader(path);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factory=builder.build(reader);
			session=factory.openSession();
			result=session.insert("com.vote.dao.Vote_userDao.doregister", vu);
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

}
