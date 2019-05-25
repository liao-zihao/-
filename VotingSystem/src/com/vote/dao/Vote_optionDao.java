package com.vote.dao;

import java.util.List;

import com.vote.bean.Vote_option;

public interface Vote_optionDao {

	List<Vote_option> searchoption(int sid);

}
