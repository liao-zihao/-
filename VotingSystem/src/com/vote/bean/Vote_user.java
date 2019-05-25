package com.vote.bean;
/**
 * 用户信息表
 * @author hasee
 * @version 刘杰 2017-10-26
 */
public class Vote_user {
	/**
	 * 用户id
	 */
	private int vu_user_id;
	/**
	 * 用户名
	 */
	private String vu_user_name;
	
	/**
	 * 密码
	 */
	private String vu_password;

	public int getVu_user_id() {
		return vu_user_id;
	}

	public void setVu_user_id(int vu_user_id) {
		this.vu_user_id = vu_user_id;
	}

	public String getVu_user_name() {
		return vu_user_name;
	}

	public void setVu_user_name(String vu_user_name) {
		this.vu_user_name = vu_user_name;
	}

	public String getVu_password() {
		return vu_password;
	}

	public void setVu_password(String vu_password) {
		this.vu_password = vu_password;
	}
	
}
