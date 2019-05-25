package com.traininfo.bean;
/**
 * 车票信息类
 * @author hasee
 * @version LJ 2017-10-9
 */
public class TrainInfo {
	/**
	 * 车次编号
	 */
	private String train_no;
	
	/**
	 * 始发站
	 */
	private String start_station;
	
	/**
	 * 终点站
	 */
	private String arrival_station;
	
	/**
	 * 出发时间
	 */
	private String start_time;
	
	/**
	 * 到达时间
	 */
	private String arrival_time;
	
	/**
	 * 车次类型
	 */
	private String type;
	
	/**
	 * 运行时间
	 */
	private String runtime;
	
	/**
	 * 里程
	 */
	private int mile;
	/**
	 * 车次编号
	 */
	public String getTrain_no() {
		return train_no;
	}

	/**
	 * 车次编号
	 */
	public void setTrain_no(String train_no) {
		this.train_no = train_no;
	}

	/**
	 * 始发站
	 */
	public String getStart_station() {
		return start_station;
	}

	/**
	 * 始发站
	 */
	public void setStart_station(String start_station) {
		this.start_station = start_station;
	}

	/**
	 * 终点站
	 */
	public String getArrival_station() {
		return arrival_station;
	}

	/**
	 * 终点站
	 */
	public void setArrival_station(String arrival_station) {
		this.arrival_station = arrival_station;
	}

	/**
	 * 出发时间
	 */
	public String getStart_time() {
		return start_time;
	}

	/**
	 * 出发时间
	 */
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	/**
	 * 到达时间
	 */
	public String getArrival_time() {
		return arrival_time;
	}

	/**
	 * 到达时间
	 */
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	/**
	 * 车次类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 车次类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 运行时间
	 */
	public String getRuntime() {
		return runtime;
	}

	/**
	 * 运行时间
	 */
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	/**
	 * 里程
	 */
	public int getMile() {
		return mile;
	}

	/**
	 * 里程
	 */
	public void setMile(int mile) {
		this.mile = mile;
	}
	
	
}
