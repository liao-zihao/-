package com.vote.service.impl;

import com.vote.bean.Vote_item;
import com.vote.dao.Vote_itemDao;
import com.vote.dao.impl.Vote_itemDaoImpl;
import com.vote.service.Vote_itemService;

public class Vote_itemServiceImpl implements Vote_itemService{
	Vote_itemDao vid=new Vote_itemDaoImpl();
	public int addvote(Vote_item vi) {
		return vid.addvote(vi);
		
	}

}
