package com.patient.list;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class PatientListServlet extends HttpServlet {

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
    	String action = request.getServletPath();
    	System.out.println(request.getContextPath()+action);
		switch (action) {
		case "/add":
			addPatient(request, response);
			break;
		case "/delete":
			deletePatient(request, response);
			break;
		case "/update":
			updatePatient(request, response);
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/list":
			displayPatientList(request, response);
			break;
		default:
			
			break;
		}
        
    }

    private void addPatient(HttpServletRequest request, HttpServletResponse response) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String patientName = request.getParameter("patientName");
            String patientAge = request.getParameter("patientAge");
            String patientAddress = request.getParameter("patientAddress");

            String insertQuery = "INSERT INTO patient (patientName, patientAge, patientAddress) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, patientName);
            insertStatement.setString(2, patientAge);
            insertStatement.setString(3, patientAddress);
            insertStatement.executeUpdate();
            insertStatement.close();

            response.sendRedirect("list");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            int patientID = Integer.parseInt(request.getParameter("patientID"));

            String deleteQuery = "DELETE FROM patient WHERE patientID = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, patientID);
            deleteStatement.executeUpdate();

            response.sendRedirect("list");
            deleteStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            int patientID = Integer.parseInt(request.getParameter("patientID"));
            String patientName = request.getParameter("patientName");
            String patientAge = request.getParameter("patientAge");
            String patientAddress = request.getParameter("patientAddress");

            String updateQuery = "UPDATE patient SET patientName = ?, patientAge = ?, patientAddress = ? WHERE patientID = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, patientName);
            updateStatement.setString(2, patientAge);
            updateStatement.setString(3, patientAddress);
            updateStatement.setInt(4, patientID);
            updateStatement.executeUpdate();

            response.sendRedirect("list");
            updateStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            int patientID = Integer.parseInt(request.getParameter("patientID"));

            String selectQuery = "SELECT * FROM patient WHERE patientID = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setInt(1, patientID);
            ResultSet resultSet = selectStatement.executeQuery();

            Patient patient = null;
            if (resultSet.next()) {
                String name = resultSet.getString("patientName");
                String age = resultSet.getString("patientAge");
                String address = resultSet.getString("patientAddress");
                patient = new Patient(patientID, name, age, address);
            }

            resultSet.close();
            selectStatement.close();
            connection.close();

            request.setAttribute("patient", patient);
            request.getRequestDispatcher("updatePatientList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayPatientList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String selectQuery = "SELECT * FROM patient";
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(selectQuery);
           
            List<Patient> patientList = new ArrayList<>();

            while (resultSet.next()) {
                int patientID = resultSet.getInt("patientID");
                String name = resultSet.getString("patientName");
                String age = resultSet.getString("patientAge");
                String address = resultSet.getString("patientAddress");
                patientList.add(new Patient(patientID, name, age, address));
            }
        
            resultSet.close();
            selectStatement.close();
            connection.close();

            request.setAttribute("patientList", patientList);
            RequestDispatcher dispatch =request.getRequestDispatcher("showPatientList.jsp");
            dispatch.forward(request, response);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
