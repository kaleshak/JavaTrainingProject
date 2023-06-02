package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Login;

public class LoginDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
	private String jdbcname = "root";
	private String jdbcPassword = "root";

	public String validate(Login login) throws ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcname, jdbcPassword);

				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from emp where name = ? and password = ?")) {
			preparedStatement.setString(1, login.getName());
			preparedStatement.setString(2, login.getPassword());

			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				connection.close();
				return name;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
