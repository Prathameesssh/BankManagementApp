package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.AdminService;
import com.aurionpro.service.UserService;

/**
 * Servlet implementation class RejectAccountController
 */
@WebServlet("/RejectAccountController")
public class RejectAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RejectAccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("userId"));

		AdminService adminService = new AdminService();
		boolean isRejected = adminService.rejectUser(userId);

		if (isRejected) {
			// Redirect back to Admin Dashboard after rejecting
			request.getRequestDispatcher("/WEB-INF/views/RejectPage.jsp").forward(request, response);
		} else {
			// Show error page if something failed
			response.sendRedirect("error.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
