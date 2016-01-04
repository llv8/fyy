package com.fy.fyy.back.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtil {
	private static Logger logger = LoggerFactory.getLogger(DBUtil.class);
	private static final String URL = "jdbc:mysql://localhost:3306/fyy?"
			+ "user=root&password=root&useUnicode=true&characterEncoding=UTF8";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			logger.info("mysql driver load success.");
		} catch (ClassNotFoundException e) {
			logger.error("mysql driver load fail!");
		}
	}

	public static int update(String sql) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL);
			// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			return result;
		} catch (SQLException e) {
			logger.error("mysql operator fail!");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {

			}
		}
		return 0;
	}

	public static ResultSet query(String sql, String[] params) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL);
			// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			Statement stmt = conn.prepareStatement(sql, params);
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			logger.error("mysql operator fail!");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {

			}
		}
		return null;
	}
}
