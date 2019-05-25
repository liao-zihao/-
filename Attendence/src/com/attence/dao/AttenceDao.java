package com.attence.dao;

import java.util.List;

import com.attence.bean.Attence;
/**
 * 考勤信息数据访问接口
 * @author hasee
 * @version LJ 2017-10-16
 */
public interface AttenceDao {

	List<Attence> searchall();

	int addatt(Attence attence);

}
