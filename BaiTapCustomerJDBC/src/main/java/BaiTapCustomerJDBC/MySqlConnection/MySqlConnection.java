package BaiTapCustomerJDBC.MySqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	private static String url = "jdbc:mysql://localhost:3306/customerdb";
	private static String username = "root";
	private static String password = "1234";

	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection != null)
			return connection;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Can not find MySQL DB Driver!!!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Can not connect to database due to: invalid url or invalid credential");
		}

		return connection;
	}
}

