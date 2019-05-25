package com.vote.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vote.bean.Vote_item;
import com.vote.bean.Vote_option;
import com.vote.dao.Vote_itemDao;

public class Vote_itemDaoImpl implements Vote_itemDao{
	String path="Vote.xml";
	public int addvote(Vote_item vi) {
		Reader reader =null;
		SqlSession session=null;
		int result=0;
		try {
			reader =Resources.getResourceAsReader(path);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factory=builder.build(reader);
			session=factory.openSession();
			result=session.insert("com.vote.dao.Vote_optionDao.searchoption",vi);
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
