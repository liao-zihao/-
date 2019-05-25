package com.vote.bean;
/**
 * 投票取值表
 * @author hasee
 * @version 刘杰 2017-10-26
 */
public class Vote_item {
	/**
	 * 编号
	 */
	private int vi_id;
	
	/**
	 * 投票选项id
	 */
	private int vo_id;
	
	/**
	 * 投票内容id
	 */
	private int vs_id;
	
	/**
	 * 用户id
	 */
	private int vs_user_id;

	public int getVi_id() {
		return vi_id;
	}

	public void setVi_id(int vi_id) {
		this.vi_id = vi_id;
	}

	public int getVo_id() {
		return vo_id;
	}

	public void setVo_id(int vo_id) {
		this.vo_id = vo_id;
	}

	public int getVs_id() {
		return vs_id;
	}

	public void setVs_id(int vs_id) {
		this.vs_id = vs_id;
	}

	public int getVs_user_id() {
		return vs_user_id;
	}

	public void setVs_user_id(int vs_user_id) {
		this.vs_user_id = vs_user_id;
	}
	
}
