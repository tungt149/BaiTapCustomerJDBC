package BaiTapCustomerJDBC.DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import BaiTapCustomerJDBC.Model.CustomerModel;
import BaiTapCustomerJDBC.MySqlConnection.MySqlConnection;

public class CustomerDataAccessLayer {

	public List<CustomerModel> findAll() {
		List<CustomerModel> customers = new ArrayList<CustomerModel>();
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "SELECT * FROM customers";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				CustomerModel customer = new CustomerModel();
				customer.setCode(result.getInt("code"));
				customer.setName(result.getString("name"));
				customer.setEmail(result.getString("email"));
				customer.setAddress(result.getString("address"));
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}

	public CustomerModel findCode(int code) {
		CustomerModel customer = new CustomerModel();
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "SELECT * FROM customers WHERE code  =?";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				customer.setCode(result.getInt("code"));
				customer.setName(result.getString("name"));
				customer.setEmail(result.getString("email"));
				customer.setAddress(result.getString("address"));
			}

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return customer;
	}

	public int add(CustomerModel customer) {
		int result = -1;
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "INSERT INTO customers(name,email,address) VALUES (?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getEmail());
			statement.setString(3, customer.getAddress());
			result = statement.executeUpdate();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int update(CustomerModel customer) {
		int result = -1;
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "UPDATE customers SET  name =?, email =?, address=? WHERE code =?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getEmail());
			statement.setString(3, customer.getAddress());
			statement.setInt(4, customer.getCode());
			result = statement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int delete(int code) {
		int result = -1;
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = " DELETE FROM customers WHERE code =?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, code);
			result = statement.executeUpdate();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
