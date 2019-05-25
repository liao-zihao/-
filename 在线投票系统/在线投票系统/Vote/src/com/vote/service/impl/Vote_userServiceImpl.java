package com.vote.service.impl;

import com.vote.bean.Vote_user;
import com.vote.dao.Vote_userDao;
import com.vote.dao.impl.Vote_userDaoImpl;
import com.vote.service.Vote_userService;

public class Vote_userServiceImpl implements Vote_userService{
	Vote_userDao vud=new Vote_userDaoImpl();
	public int searchUname(Vote_user vu) {
		
		return vud.searchUname(vu);
	}
	public int doregister(Vote_user vu) {
		
		return vud.doregister(vu);
	}

}
