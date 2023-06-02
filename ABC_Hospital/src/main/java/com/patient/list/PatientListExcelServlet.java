package com.patient.list;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet("/generate-excel")
public class PatientListExcelServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:mysql://localhost:3306/training";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = dateFormat.format(new Date());
		String filename = "patient_data_" + currentTime + ".xlsx";

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		Workbook workbook = new XSSFWorkbook();

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String query = "SELECT patientID, patientName, patientAge, patientAddress FROM patient";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			Sheet sheet = workbook.createSheet("Patient List");

			createHeaderRow(sheet);
			int rowNum = 1;
			while (resultSet.next()) {
				createDataRow(sheet, rowNum++, resultSet.getString("patientID"), resultSet.getString("patientName"),
						resultSet.getString("patientAge"), resultSet.getString("patientAddress"));
			}

			// Adjust column widths
			for (int i = 0; i < 4; i++) {
				sheet.autoSizeColumn(i);
			}

			OutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);

			workbook.close();
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createHeaderRow(Sheet sheet) {

		Row headerRow = sheet.createRow(0);

		Cell cell = headerRow.createCell(0);
		cell.setCellValue("Patient ID");

		cell = headerRow.createCell(1);
		cell.setCellValue("Patient Name");

		cell = headerRow.createCell(2);
		cell.setCellValue("Patient Age");

		cell = headerRow.createCell(3);
		cell.setCellValue("Patient Address");
	}

	private void createDataRow(Sheet sheet, int rowNum, String patientID, String patientName, String patientAge,
			String patientAddress) {
		Row row = sheet.createRow(rowNum);

		Cell cell = row.createCell(0);
		cell.setCellValue(patientID);

		cell = row.createCell(1);
		cell.setCellValue(patientName);

		cell = row.createCell(2);
		cell.setCellValue(patientAge);

		cell = row.createCell(3);
		cell.setCellValue(patientAddress);
	}
}
