package com.vote.dao;

import java.util.List;

import com.vote.vo.Page;
import com.vote.vo.ShowVo;

public interface Vote_subjectDao {

	int searchAllCount();

	List<ShowVo> showall(Page p);

}
