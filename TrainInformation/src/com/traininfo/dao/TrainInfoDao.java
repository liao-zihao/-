package com.traininfo.dao;

import java.util.List;

import com.traininfo.bean.TrainInfo;
/**
 * 车票信息数据访问接口
 * @author hasee
 * @version LJ 2017-10-9
 */
public interface TrainInfoDao {
	/**
	 * 获取所有车票信息方法
	 * @return List<TrainInfo>
	 */
	List<TrainInfo> getTrainInfo();

	/**
	 * 获取车票信息方法
	 * @return TrainInfo
	 */
	TrainInfo shwotrain(String train_no);

	/**
	 * 删除车票信息方法
	 * @return TrainInfo
	 */
	void deltrain(String s);

}
