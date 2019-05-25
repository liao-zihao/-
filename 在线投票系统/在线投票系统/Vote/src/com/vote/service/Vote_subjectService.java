package com.vote.service;

import java.util.List;

import com.vote.vo.Page;
import com.vote.vo.ShowVo;

public interface Vote_subjectService {

	int searchAllCount();

	List<ShowVo> showall(Page p);

}
