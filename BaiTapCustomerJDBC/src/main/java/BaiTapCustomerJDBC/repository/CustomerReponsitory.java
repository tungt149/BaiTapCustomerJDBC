package BaiTapCustomerJDBC.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import BaiTapCustomerJDBC.Model.CustomerModel;
import BaiTapCustomerJDBC.MySqlConnection.MySqlConnection;



public class CustomerReponsitory {
	public List<CustomerModel> findAllCustomer() {
		List<CustomerModel> customers = new LinkedList<CustomerModel>();

		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "SELECT code, name, address, email FROM customer";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				CustomerModel customer = new CustomerModel();

				customer.setCode(resultSet.getInt("code"));
				
				customer.setName(resultSet.getNString("name"));
				customer.setAddress(resultSet.getNString("address"));
				customer.setEmail(resultSet.getString("email"));

				customers.add(customer);
			}

		} catch (SQLException e) {
			System.out.println("Connection to database has been disconnected!");
			e.printStackTrace();
		}

		return customers;
	}

	public CustomerModel findCustomerByCode(int code) {
		CustomerModel customer = null;

		try {
			Connection connection = MySqlConnection.getConnection();

			String query = "SELECT code, name, address, email FROM customer WHERE code = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, code);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				customer = new CustomerModel();
				customer.setCode(resultSet.getInt("code"));
				;
				customer.setName(resultSet.getNString("name"));
				customer.setAddress(resultSet.getNString("address"));
				customer.setEmail(resultSet.getString("email"));
			}

		} catch (SQLException e) {
			System.out.println("Connection to database has been disconnected!");
			e.printStackTrace();
		}

		return customer;
	}

	public int addNewCustomer(CustomerModel customer) {
		int result = -1;
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "INSERT INTO customers(name, address, email) values( ?, ?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getAddress());
			statement.setString(3, customer.getEmail());
			result = statement.executeUpdate();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteCustomerByCode(int code) {
		int result = -1;
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "DELETE FROM customers WHERE code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, code);
			result = statement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateCustomer(CustomerModel customer) {
		int result = -1;
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "UPDATE customers SET name = ?, address = ?, email = ? WHERE code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, customer.getCode());
			statement.setString(2, customer.getName());
			statement.setString(3, customer.getAddress());
			statement.setString(4, customer.getEmail());
			result = statement.executeUpdate();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}