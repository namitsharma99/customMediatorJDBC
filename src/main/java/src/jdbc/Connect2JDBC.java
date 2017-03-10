package src.jdbc;

import java.sql.*;

public class Connect2JDBC {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/namit_schema";
	static final String USER = "root";
	static final String PASS = "Intel@01";

	public static Connection getConnection() {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Class.forName(JDBC_DRIVER);
			return conn;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

}
