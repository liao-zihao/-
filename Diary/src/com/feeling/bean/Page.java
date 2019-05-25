package com.feeling.bean;

public class Page {
	/**
	 * 起始记录数
	 */
	private int start;
	/**
	 * 总页数
	 */
	private int pagenum;
	
	/**
	 * 行数
	 */
	private int rownum;
	
	/**
	 * 当前页码
	 */
	private int currpage;
	
	/**
	 * 总行数
	 */
	private int allrownum;

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public int getCurrpage() {
		return currpage;
	}

	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}

	public int getAllrownum() {
		return allrownum;
	}

	public void setAllrownum(int allrownum) {
		this.allrownum = allrownum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	
}
