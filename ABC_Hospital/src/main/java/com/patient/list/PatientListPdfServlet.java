package com.patient.list;

import java.io.IOException;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/generate-pdf")
public class PatientListPdfServlet extends HttpServlet {

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
		String filename = "patient_data_" + currentTime + ".pdf";

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		Document document = new Document();

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String query = "SELECT patientID, patientName, patientAge, patientAddress FROM patient";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			PdfWriter.getInstance(document, response.getOutputStream());

			document.open();

			/*------------Header table--------------*/
			PdfPTable table1 = new PdfPTable(2);
			float[] columnWidths = { 20f, 80f };
			table1.setWidths(columnWidths);

			PdfPCell imageCell = new PdfPCell();
			Image image = Image.getInstance("D:\\KKJava\\ABC_Hospital\\images\\logo.jpg");
			image.scaleToFit(75, 75);

			imageCell.addElement(image);
			imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			imageCell.setBorderWidth(0);
			imageCell.setBackgroundColor(new BaseColor(173, 216, 230));

			table1.addCell(imageCell);

			PdfPCell detailsCell = new PdfPCell();

			Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 128, 0));
			Paragraph para = new Paragraph("ABC Hospital", boldFont);
			para.setAlignment(Element.ALIGN_CENTER);

			Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC, new BaseColor(0, 128, 0));
			Paragraph para1 = new Paragraph(
					"no.1,xyz street ,xyz Road, \n bengaluru, karnataka-560010.\n ph.no:9876543210. \n ", font);
			para1.setAlignment(Element.ALIGN_CENTER);

			detailsCell.addElement(para);
			detailsCell.addElement(para1);
			detailsCell.setBackgroundColor(new BaseColor(173, 216, 230));
			detailsCell.setBorderWidth(0);

			table1.addCell(detailsCell);

			/*-----------------Table to show Patient List--------------------*/
			PdfPTable table2 = new PdfPTable(4);
			addTableHeader(table2);
			while (resultSet.next()) {
				addTableRow(table2, resultSet.getString("patientID"), resultSet.getString("patientName"),
						resultSet.getString("patientAge"), resultSet.getString("patientAddress"));
			}

			document.add(table1);

			Font headfont = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLUE);
			Paragraph patientList = new Paragraph("PATIENT LIST", headfont);
			patientList.setAlignment(Element.ALIGN_CENTER);
			document.add(patientList);

			document.add(new Paragraph("\n"));
			document.add(table2);

			document.close();
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException | DocumentException e) {
			e.printStackTrace();
		}
	}

	private void addTableHeader(PdfPTable table2) {
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Patient ID"));
		cell.setPadding(5);
		cell.setBackgroundColor(new BaseColor(3, 138, 255));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell);

		cell = new PdfPCell(new Phrase("Patient Name"));
		cell.setPadding(5);
		cell.setBackgroundColor(new BaseColor(3, 138, 255));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell);

		cell = new PdfPCell(new Phrase("Patient Age"));
		cell.setPadding(5);
		cell.setBackgroundColor(new BaseColor(3, 138, 255));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell);

		cell = new PdfPCell(new Phrase("Patient Address"));
		cell.setPadding(5);
		cell.setBackgroundColor(new BaseColor(3, 138, 255));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell);
	}

	private void addTableRow(PdfPTable table2, String patientID, String patientName, String patientAge,
			String patientAddress) {
		PdfPCell cell;

		cell = new PdfPCell(new Phrase(patientID));
		cell.setBackgroundColor(new BaseColor(173, 216, 230));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell);

		cell = new PdfPCell(new Phrase(patientName));
		cell.setBackgroundColor(new BaseColor(173, 216, 230));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell);

		cell = new PdfPCell(new Phrase(patientAge));
		cell.setBackgroundColor(new BaseColor(173, 216, 230));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell);

		cell = new PdfPCell(new Phrase(patientAddress));
		cell.setBackgroundColor(new BaseColor(173, 216, 230));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell);
	}

}