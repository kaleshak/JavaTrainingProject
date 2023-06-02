package com.register.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:mysql://localhost:3306/training";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String userName = request.getParameter("userName");
			String userEmail = request.getParameter("userEmail");
			String password = request.getParameter("password");

			String insertQuery = "INSERT INTO registereduser (userName, userEmail, password) VALUES (?, ?, ?)";
			PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, userName);
			insertStatement.setString(2, userEmail);
			insertStatement.setString(3, password);
			insertStatement.executeUpdate();
			insertStatement.close();

			request.setAttribute("successMessage", "Registration successful!");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
