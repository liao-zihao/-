package com.vote.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vote.bean.Vote_option;
import com.vote.dao.Vote_optionDao;

public class Vote_optionDaoImpl implements Vote_optionDao{
	String path="Vote.xml";
	public List<Vote_option> searchoption(int sid) {
		List<Vote_option> olist=new ArrayList<Vote_option>();
			Reader reader =null;
			SqlSession session=null;
			try {
				reader =Resources.getResourceAsReader(path);
				SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
				SqlSessionFactory factory=builder.build(reader);
				session=factory.openSession();
				olist=session.selectList("com.vote.dao.Vote_optionDao.searchoption",sid);
				
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
			return olist;
	}

}
