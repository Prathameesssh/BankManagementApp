package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.model.Account;
import com.aurionpro.service.UserService;

/**
 * Servlet implementation class UserAccountController
 */
@WebServlet("/UserAccountController")
public class UserAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get account number from request parameter
		String accountNumber = request.getParameter("accountNumber");

		// Here you can fetch the user account details from DB/service
		// Example:
		Account userAccount = UserService.getAccount(accountNumber);

		if (userAccount == null) {

			response.sendRedirect("ErrorController");
			return;
		}
		request.setAttribute("account", userAccount);

		// Forward to JSP
		request.getRequestDispatcher("/WEB-INF/views/UserAccount.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
