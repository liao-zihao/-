package com.lzh.util;



import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil02 {
	
	static ComboPooledDataSource dataSource = null;
	static{
		dataSource = new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	public static Connection getConn() throws SQLException{
		return dataSource.getConnection();
	}
	
	public static void release(Connection conn,Statement st ,ResultSet rs) {
		closeSt(st);
		closeRs(rs);
		closeConn(conn);
	}
	
	public static void release(Connection conn,Statement st ) {
		closeSt(st);
		closeConn(conn);
	}

	private static void closeConn(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			conn = null;
		}
		
	}

	private static void closeRs(ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			rs = null;
		}
		
	}

	private static void closeSt(Statement st) {
		try {
			if(st !=null) {
				st.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			st = null;
		}
	}
}