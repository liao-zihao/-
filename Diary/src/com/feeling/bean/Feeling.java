package com.feeling.bean;
/**
 * 日记实体类
 * @author hasee
 * @version LJ 2017-10-21
 */
public class Feeling {
	
	/**
	 * 日记编号
	 */
	private int f_id;

	/**
	 * 日记标题
	 */
	private String f_title;

	/**
	 * 日记内容
	 */
	private String f_content;

	/**
	 * 时间
	 */
	private String f_date;

	/**
	 * 类型
	 */
	private int f_type;

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public String getF_title() {
		return f_title;
	}

	public void setF_title(String f_title) {
		this.f_title = f_title;
	}

	public String getF_content() {
		return f_content;
	}

	public void setF_content(String f_content) {
		this.f_content = f_content;
	}

	public String getF_date() {
		return f_date;
	}

	public void setF_date(String f_date) {
		this.f_date = f_date;
	}

	public int getF_type() {
		return f_type;
	}

	public void setF_type(int f_type) {
		this.f_type = f_type;
	}
}
