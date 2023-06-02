package com.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.LoginDao;
import com.model.Login;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginDao loginDao;

	public void init() {
		loginDao = new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		authenticate(request, response);

	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		Login login = new Login();
		login.setName(name);
		login.setPassword(password);
		
		try {
			if (loginDao.validate(login)!= null) {
				
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(1*30	);
				session.setAttribute("name", name);
				
				request.getRequestDispatcher("list").forward(request, response);


			} 
			else {
				request.setAttribute("INVALID", "Invalid Credentials Or Please SignUp!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
