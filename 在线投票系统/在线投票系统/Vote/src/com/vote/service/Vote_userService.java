package com.vote.service;

import com.vote.bean.Vote_user;

public interface Vote_userService {

	int searchUname(Vote_user vu);

	int doregister(Vote_user vu);

}
