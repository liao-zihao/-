package com.attence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.attence.bean.Attence;
import com.attence.dao.AttenceDao;
import com.attence.dao.BaseDao;
/**
 * 考勤信息数据访问类
 * @author hasee
 * @version LJ 2017-10-16
 */
public class AttenceDaoImpl extends BaseDao implements AttenceDao{
	/**
	 * 查找所有方法
	 */
	public List<Attence> searchall() {
		List<Attence> list=new ArrayList<Attence>();
		String sql="select id,empname,dept,chkdate,status from attence";
		ResultSet rs= search(sql);
		try {
			while(rs.next()){
				Attence a=new Attence();
				a.setChkdate(rs.getString("chkdate"));
				a.setDept(rs.getString("dept"));
				a.setEmpname(rs.getString("empname"));
				a.setId(rs.getInt("id"));
				a.setStatus(rs.getString("status"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
	/**
	 * 查找信息方法
	 */
	public int addatt(Attence attence) {
		String sql="insert into attence (empname,dept,chkdate,status) values(?,?,?,?)";
		int result=0;
		try {
			result=update(sql, attence.getEmpname(),attence.getDept(),attence.getChkdate(),attence.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return result;
	}

}
