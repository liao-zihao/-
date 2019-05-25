package com.attence.service.impl;

import java.util.List;

import com.attence.bean.Attence;
import com.attence.dao.AttenceDao;
import com.attence.dao.impl.AttenceDaoImpl;
import com.attence.service.AttenceService;
/**
 * 考勤信息业务逻辑类
 * @author hasee
 * @version LJ 2017-10-16
 */
public class AttenceServiceImpl implements AttenceService{
	AttenceDao ad=new AttenceDaoImpl();
	public List<Attence> searchall() {
		return ad.searchall();
	}
	public int addatt(Attence attence) {
		return ad.addatt(attence);
	}
	
}
