package com.lzh.crm.mapper;

import java.util.List;

import com.lzh.crm.pojo.BaseDict;

public interface BaseDictDao {

	
	//查询
	public List<BaseDict> selectBaseDictListByCode(String code);
}
