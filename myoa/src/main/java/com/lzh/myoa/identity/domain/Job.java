package com.lzh.myoa.identity.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity @Table(name="oa_id_job")
public class Job implements Serializable{
	private static final long serialVersionUID=2L;
	@Id @Column(name="code",length=100)
	private String code;
	@Column(name="name",length=50)
	private String name;
	@Column(name="remark",length=300)
	private String remark;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
