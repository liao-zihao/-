package com.feeling.dao;

import java.util.List;

import com.feeling.bean.Feeling;
import com.feeling.bean.Page;
/**
 * 日记数据访问接口
 * @author hasee
 * @version LJ 2017-10-21
 */
public interface FeelingDao {

	List<Feeling> showAll(Page p);

	int searchAllCount();

	int addFeeling(Feeling feeling);

	Feeling searchFeeling(int id);

}
