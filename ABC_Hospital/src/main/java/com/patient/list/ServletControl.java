package com.patient.list;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ServletControl")
public class ServletControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String fileType = request.getParameter("fileType");
	    int fileTypeInt = Integer.parseInt(fileType);

	    if (fileTypeInt != 0) {
	        switch (fileTypeInt) {
	            case 1:
	                response.sendRedirect(request.getContextPath() + "/generate-pdf");
	                break;
	            case 2:
	                response.sendRedirect(request.getContextPath() + "/generate-excel");
	                break;
	            case 3:
	                response.sendRedirect(request.getContextPath() + "/generate-csv");
	                break;
	            default:
	                response.getWriter().println("Invalid report type");
	                break;
	        }
	    } else {
	        response.getWriter().println("Report type not specified");
	    }
	}


}
