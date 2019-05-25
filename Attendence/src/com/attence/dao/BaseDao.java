package com.attence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDao {
	Connection conn = null;
	PreparedStatement state = null;
	ResultSet rs = null;
	/**
	 * 获取连接对象
	 * @return Connection
	 */
	public Connection getConn(){
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection( "jdbc:mysql://localhost/test?user=root&password=root&useUnicode=true&characterEncoding=utf-8","root","123456");
		
			Context cont = new InitialContext();
			DataSource ds = (DataSource)cont.lookup("java:comp/env/lj");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	/**
	 * @return int
	 * 执行增删改
	 */
	public int update(String sql,Object...args) {
		conn = getConn();
		int result = 0;
		
		try {
			state = conn.prepareStatement(sql);
			for(int i = 0;i<args.length;i++){
				state.setObject(i+1, args[i]);
			}
			result=state.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return result;
	}
	
	/**
	 * @return ResultSet
	 * 执行查询
	 */
	public ResultSet search(String sql,Object...args){
		conn = getConn();
		try {
			state = conn.prepareStatement(sql);
			for(int i = 0;i<args.length;i++){
				state.setObject(i+1, args[i]);
			}
			rs=state.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 关闭对象
	 */
	public void close(){
		
		try {
			if(rs!=null){
				rs.close();
			}
			if(state!=null){
				state.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
