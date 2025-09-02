package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.User;
import com.aurionpro.service.UserService;

@WebServlet("/LoginPageController")
public class LoginPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginPageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = UserService.getUser(email, password);

		if (user == null) {
			request.setAttribute("errorMessage", "Invalid Credentials! Please try again.");
			request.getRequestDispatcher("/WEB-INF/views/LoginPage.jsp").forward(request, response);
			return;
		}

		if (!user.isVerified()) {
			request.getRequestDispatcher("/WEB-INF/views/waitingRoom.jsp").forward(request, response);
			return;
		}

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		if (user.isAdmin()) {
			response.sendRedirect("AdminDashboardController");
			return;
		}

		response.sendRedirect("UserDashboardController");

	}
}
