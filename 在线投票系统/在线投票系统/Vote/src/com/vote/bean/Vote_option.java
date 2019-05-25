package com.vote.bean;
/**
 * 投票选项表
 * @author hasee
 * @version 刘杰 2017-10-26
 */
public class Vote_option {
	/**
	 * 编号
	 */
	private int vo_id;
	/**
	 * 投票选项
	 */
	private String vo_option;
	
	/**
	 * 投票内容id
	 */
	private int vs_id;
	
	/**
	 * 显示顺序
	 */
	private int vo_order;

	public int getVo_id() {
		return vo_id;
	}

	public void setVo_id(int vo_id) {
		this.vo_id = vo_id;
	}

	public String getVo_option() {
		return vo_option;
	}

	public void setVo_option(String vo_option) {
		this.vo_option = vo_option;
	}

	public int getVs_id() {
		return vs_id;
	}

	public void setVs_id(int vs_id) {
		this.vs_id = vs_id;
	}

	public int getVo_order() {
		return vo_order;
	}

	public void setVo_order(int vo_order) {
		this.vo_order = vo_order;
	}
	
}
