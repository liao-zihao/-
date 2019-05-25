package com.vote.vo;


/**
 * 显示投票内容工具类
 * @author Hao-PC
 *
 */
public class ShowVo {


	/**
	 *  投票内容id
	 */
	private int sid;
	
	/**
	 * 投票内容
	 */
	private String title;
	
	/**
	 * 投票类型
	 */
	private int type;
	
	/**
	 * 选项数
	 */
	private int numoptions;
	
	/**
	 * 投票人数
	 */
	private int votenum;
	

	/**
	 * 获取投票内容id
	 * @return int
	 */
	public int getSid() {
		return sid;
	}
	
	/**
	 * 设置投票内容id
	 * @param sid
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	/**
	 * 获取投票内容
	 * @return String
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 设置投票内容
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取投票类型
	 * @return int
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * 设置投票类型
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * 获取投票选项数
	 * @return int
	 */
	public int getNumoptions() {
		return numoptions;
	}

	/**
	 * 设置投票选项数
	 * @param numoptions
	 */
	public void setNumoptions(int numoptions) {
		this.numoptions = numoptions;
	}

	/**
	 * 获取投票人数
	 * @return int
	 */
	public int getVotenum() {
		return votenum;
	}

	/**
	 * 设置投票人数
	 * @param votenum
	 */
	public void setVotenum(int votenum) {
		this.votenum = votenum;
	}
}
