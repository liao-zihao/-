package com.vote.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vote.dao.Vote_subjectDao;
import com.vote.vo.Page;
import com.vote.vo.ShowVo;

public class Vote_subjectDaoImpl implements Vote_subjectDao{
	String path="Vote.xml";
	public int searchAllCount() {
		int result=0;
		Reader reader =null;
		SqlSession session=null;
		try {
			reader =Resources.getResourceAsReader(path);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factory=builder.build(reader);
			session=factory.openSession();
			result=session.selectOne("com.vote.dao.Vote_subjectDao.searchAllCount");
			
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

	public List<ShowVo> showall(Page p) {
		List<ShowVo> list=new ArrayList<ShowVo>();
		Reader reader =null;
		SqlSession session=null;
		try {
			reader =Resources.getResourceAsReader(path);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factory=builder.build(reader);
			session=factory.openSession();
			list=session.selectList("com.vote.dao.Vote_subjectDao.showall", p);
			
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

}
