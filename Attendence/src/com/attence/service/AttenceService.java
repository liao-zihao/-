package com.attence.service;

import java.util.List;

import com.attence.bean.Attence;
/**
 * 考勤信息业务逻辑接口
 * @author hasee
 * @version LJ 2017-10-16
 */
public interface AttenceService {

	List<Attence> searchall();

	int addatt(Attence attence);

}
