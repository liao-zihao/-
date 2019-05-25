package com.traininfo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.traininfo.bean.TrainInfo;
import com.traininfo.dao.BaseDao;
import com.traininfo.dao.TrainInfoDao;
/**
 * 车票信息数据访问类
 * @author hasee
 * @version LJ 2017-10-9
 */
public class TrainInfoDaoImpl extends BaseDao implements TrainInfoDao{
	/**
	 * 获取所有车票信息方法
	 * @return List<TrainInfo>
	 */
	

	
	public List<TrainInfo> getTrainInfo() {
		List<TrainInfo> list=new ArrayList<TrainInfo>();
		String sql="select train_no,start_station,arrival_station,start_time,arrival_time,type,runtime from train_info";
		ResultSet rs= search(sql);
		
		try {
			while(rs.next()){
				TrainInfo trainInfo=new TrainInfo();
				trainInfo.setTrain_no(rs.getString("train_no"));
				trainInfo.setStart_station(rs.getString("start_station"));
				trainInfo.setArrival_station(rs.getString("arrival_station"));
				trainInfo.setStart_time(rs.getString("start_time"));
				trainInfo.setArrival_time(rs.getString("arrival_time"));
				trainInfo.setType(rs.getString("type"));
				trainInfo.setRuntime(rs.getString("runtime"));
				list.add(trainInfo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		
		return list;
	}

	/**
	 * 获取车票信息方法
	 * @return TrainInfo
	 */
	public TrainInfo shwotrain(String train_no) {
		String sql="select train_no,start_station,arrival_station,start_time,arrival_time,type,runtime,mile from train_info where train_no=?";
		ResultSet rs= search(sql,train_no);
		TrainInfo trainInfo=null;
		try {
			while(rs.next()){
				trainInfo=new TrainInfo();
				trainInfo.setTrain_no(rs.getString("train_no"));
				trainInfo.setStart_station(rs.getString("start_station"));
				trainInfo.setArrival_station(rs.getString("arrival_station"));
				trainInfo.setStart_time(rs.getString("start_time"));
				trainInfo.setArrival_time(rs.getString("arrival_time"));
				trainInfo.setType(rs.getString("type"));
				trainInfo.setRuntime(rs.getString("runtime"));
				trainInfo.setMile(rs.getInt("mile"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return trainInfo;
	}


	/**
	 * 删除车票信息方法
	 * @return TrainInfo
	 */
	public void deltrain(String s) {
		String sql="delete from train_info where train_no=?";
		update(sql, s);
	}
	
}
