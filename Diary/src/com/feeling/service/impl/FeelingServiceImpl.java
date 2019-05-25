package com.feeling.service.impl;

import java.util.List;

import com.feeling.bean.Feeling;
import com.feeling.bean.Page;
import com.feeling.dao.FeelingDao;
import com.feeling.dao.impl.FeelingDaoImpl;
import com.feeling.service.FeelingService;
/**
 * 日记业务逻辑实现类
 * @author hasee
 * @version LJ 2017-10-21
 */
public class FeelingServiceImpl implements FeelingService{
	FeelingDao fd=new FeelingDaoImpl();
	
	public List<Feeling> showAll(Page p) {
		return fd.showAll(p);
	}

	public int searchAllCount() {
		return fd.searchAllCount();
	}

	public int addFeeling(Feeling feeling) {
		
		return fd.addFeeling(feeling);
	}

	public Feeling searchFeeling(int id) {
		
		return fd.searchFeeling(id);
	}

}
