package com.vote.service.impl;

import java.util.List;

import com.vote.bean.Vote_option;
import com.vote.dao.Vote_optionDao;
import com.vote.dao.impl.Vote_optionDaoImpl;
import com.vote.service.Vote_optionService;

public class Vote_optionServiceImpl implements Vote_optionService{
	
	Vote_optionDao vod=new Vote_optionDaoImpl();
	public List<Vote_option> searchoption(int sid) {
		// TODO Auto-generated method stub
		return vod.searchoption(sid);
	}

}
