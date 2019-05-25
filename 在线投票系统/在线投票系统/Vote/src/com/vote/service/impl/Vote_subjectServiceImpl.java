package com.vote.service.impl;

import java.util.List;

import com.vote.dao.Vote_subjectDao;
import com.vote.dao.impl.Vote_subjectDaoImpl;
import com.vote.service.Vote_subjectService;
import com.vote.vo.Page;
import com.vote.vo.ShowVo;

public class Vote_subjectServiceImpl implements Vote_subjectService{

	Vote_subjectDao ssd=new Vote_subjectDaoImpl();
	public int searchAllCount() {
		// TODO Auto-generated method stub
		return ssd.searchAllCount();
	}

	public List<ShowVo> showall(Page p) {
		// TODO Auto-generated method stub
		return ssd.showall(p);
	}

}
