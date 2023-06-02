package com.register.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.mail.Mailer;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:mysql://localhost:3306/training";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userEmail = request.getParameter("userEmail");
		String password = request.getParameter("password");

		String userName = isValidUser(userEmail, password);
		if (userName != null) {

			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(1 * 30);
			session.setAttribute("userEmail", userEmail);
			request.setAttribute("welcomeMessage", "Welcome, " + userName + " !");

			String subject = "Login -reg";
			String msg = "Hello, " + userName + "\nThank you visiting this site";
			Mailer.send(userEmail, subject, msg);

			request.getRequestDispatcher("list").forward(request, response);

		} else {
			request.setAttribute("errMessage", "username and password is not matched");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

	private String isValidUser(String userEmail, String password) {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String query = "SELECT * FROM registereduser WHERE userEmail = ? AND password = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, userEmail);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String userName = rs.getString("userName");
				connection.close();
				return userName;
			}

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
