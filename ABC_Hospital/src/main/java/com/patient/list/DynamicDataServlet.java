package com.patient.list;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/DynamicDataServlet")
public class DynamicDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:mysql://localhost:3306/training";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String query = "SELECT category_id, name FROM category";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			List<Category> fileType = new ArrayList<>();

			while (resultSet.next()) {
				int id = resultSet.getInt("category_id");
				String name = resultSet.getString("name");
				fileType.add(new Category(id, name));
			}
			request.setAttribute("fileType", fileType);

			Gson gson = new Gson();
			String json = gson.toJson(fileType);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();

			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
