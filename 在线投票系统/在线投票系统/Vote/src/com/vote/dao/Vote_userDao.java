package com.vote.dao;

import com.vote.bean.Vote_user;

public interface Vote_userDao {

	int searchUname(Vote_user vu);

	int doregister(Vote_user vu);

}
