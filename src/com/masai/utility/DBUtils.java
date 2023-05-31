package com.masai.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtils {
	static String URL;
	static String user;
	static String password;

	static {
		ResourceBundle rs = ResourceBundle.getBundle("db");
		
		URL = rs.getString("URL");
		user = rs.getString("USERNAME");
		password = rs.getString("PASSWORD");
	}
	
	public static Connection connect() throws SQLException {
		return  DriverManager.getConnection(URL, user, password);
	}
	
	public static boolean isResultSetEmpty(ResultSet resultSet) throws SQLException{
		return (!resultSet.isBeforeFirst() && resultSet.getRow() == 0);
	}
}
