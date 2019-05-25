package com.vote.bean;
/**
 * 投票内容表
 * @author hasee
 * @version 刘杰 2017-10-26
 */
public class Vote_subject {
	/**
	 * 编号
	 */
	private int vs_id;
	/**
	 * 投票内容
	 */
	private String vs_title;
	/**
	 * 投票类型
	 */
	private int vs_type;
	public int getVs_id() {
		return vs_id;
	}
	public void setVs_id(int vs_id) {
		this.vs_id = vs_id;
	}
	public String getVs_title() {
		return vs_title;
	}
	public void setVs_title(String vs_title) {
		this.vs_title = vs_title;
	}
	public int getVs_type() {
		return vs_type;
	}
	public void setVs_type(int vs_type) {
		this.vs_type = vs_type;
	}
	
}
