/**
 * 
 */
package com.neuedu.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleDriver;

/**
 * DESC: 
 * 2017年7月21日
 * @author BKF
 */
public class DBUtils {

	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static final String USER = "scott";
	private static final String PWD = "tiger";
	private static Connection conn;

	static {
		try {
			// 1. 驱动类的加载
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取到特定数据库的链接对象
	 * @return java.sql.Connection
	 */
	public static Connection getConnection() {
		try {
			if(conn == null || conn.isClosed()) {
				// 2. 获取链接
				conn = DriverManager.getConnection(URL, USER, PWD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 执行DML语句
	 * @param sql
	 * @return DML语句在数据库中影响的行数
	 */
	public static int modify(String sql, Object...params) {
		
		conn = getConnection();
//		Statement st = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
//			st = conn .createStatement();
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
//			result = st.executeUpdate(sql);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, null);
		}
		return result;
	}

	/**
	 * 通用的查询方法
	 * @param sql
	 * @return 结果集
	 */
	public static List<Map<String, Object>> query(String sql, Object... params) {
		
		conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			// 动态绑定参数
			for(int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			while( rs.next() ) {
				ResultSetMetaData rsmd = rs.getMetaData();
				Map<String, Object> row = new HashMap<>();
				int colNum = rsmd.getColumnCount();
				for(int i = 1; i <= colNum; i++) {
					String colName = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(colName, value);
				}
				result.add(row);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	/**
	 * 通用单行查询
	 * @param sql
	 * @return
	 */
	public static Map<String, Object> querySingle(String sql, Object... params) {

		conn = getConnection();
//		Statement st = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		Map<String, Object> result = null;
		try {
//			st = conn.createStatement();
			// 对传递的SQL进行预编译
			ps = conn.prepareStatement(sql);
			// 动态绑定参数
			for(int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
//			rs = st.executeQuery(sql);
			rs = ps.executeQuery();
			if( rs.next() ) {
				result = new HashMap<>();
				ResultSetMetaData rsmd = rs.getMetaData();
				int colNum = rsmd.getColumnCount();
				for(int i = 1; i <= colNum; i++) {
					String colName = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					result.put(colName, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}
	
	/**
	 * 通用单值查询
	 * @param sql
	 * @return
	 */
	public static Object querySingleData(String sql, Object... params) {
		
		conn = getConnection();
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		Object result = null;
		try {
			// 对传递的SQL进行预编译
			ps = conn.prepareStatement(sql);
			// 动态绑定参数
			for(int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			if( rs.next() ) {
				result = rs.getObject(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	private static void close(Connection conn, Statement st, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
