package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Account;
import com.aurionpro.service.AdminService;

/**
 * Servlet implementation class VerifyAccountController
 */
@WebServlet("/VerifyAccountController")
public class VerifyAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VerifyAccountController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("userId"));
		AdminService adminService = new AdminService();
		boolean isApproved = adminService.approveUser(userId);
		if(!isApproved) {
			return;
		}
		Account account = adminService.createAccount(userId);
		HttpSession session = request.getSession(false);
		session.setAttribute("Account", account);
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
