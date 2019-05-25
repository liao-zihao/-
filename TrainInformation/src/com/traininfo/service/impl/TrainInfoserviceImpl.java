package com.traininfo.service.impl;

import java.util.List;

import com.traininfo.bean.TrainInfo;
import com.traininfo.dao.TrainInfoDao;
import com.traininfo.dao.impl.TrainInfoDaoImpl;
import com.traininfo.service.TrainInfoService;
/**
 * 车票信息数据访问类
 * @author hasee
 * @version LJ 2017-10-9
 */
public class TrainInfoserviceImpl implements TrainInfoService{
	TrainInfoDao tid=new TrainInfoDaoImpl(); 
	/**
	 * 获取所有车票信息方法
	 * @return List<TrainInfo>
	 */



	public List<TrainInfo> getTrainInfo() {
		return tid.getTrainInfo();
	}
	
	/**
	 * 获取车票信息方法
	 * @return TrainInfo
	 */
	public TrainInfo shwotrain(String train_no) {
		return tid.shwotrain(train_no);
	}

	/**
	 * 删除车票信息方法
	 * @return TrainInfo
	 */
	public void deltrain(String s) {
		tid.deltrain(s);
		
	}

}
