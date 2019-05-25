package com.attence.bean;

/**
 * 考勤信息实体类
 * @author hasee
 * @version LJ 2017-10-16
 */
public class Attence {
	
	/**
	 * id
	 */
	private int id;
	
	/**
	 * 员工姓名
	 */
	private String empname;

	/**
	 * 部门
	 */
	private String dept;

	/**
	 * 登记日期
	 */
	private String chkdate;

	/**
	 * 考勤类型
	 */
	private String status;

	/**
	 * id
	 */
	public int getId() {
		return id;
	}

	/**
	 * id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 员工姓名
	 */
	public String getEmpname() {
		return empname;
	}

	/**
	 * 员工姓名
	 */
	public void setEmpname(String empname) {
		this.empname = empname;
	}

	/**
	 * 部门
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * 部门
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

	/**
	 * 登记日期
	 */
	public String getChkdate() {
		return chkdate;
	}

	/**
	 * 登记日期
	 */
	public void setChkdate(String chkdate) {
		this.chkdate = chkdate;
	}

	/**
	 * 考勤类型
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 考勤类型
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}	
