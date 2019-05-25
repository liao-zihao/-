package com.feeling.service;

import java.util.List;

import com.feeling.bean.Feeling;
import com.feeling.bean.Page;
/**
 * 日记业务逻辑接口
 * @author hasee
 * @version LJ 2017-10-21
 */
public interface FeelingService {

	List<Feeling> showAll(Page p);

	int searchAllCount();

	int addFeeling(Feeling feeling);

	Feeling searchFeeling(int id);

}
