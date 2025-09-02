package com.aurionpro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Account;
import com.aurionpro.model.Transaction;
import com.aurionpro.model.User;
import com.aurionpro.service.AdminService;
import com.aurionpro.service.TransactionService;
import com.aurionpro.service.UserService;

/**
 * Servlet implementation class AdminDashboardController
 */
@WebServlet("/AdminDashboardController")
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDashboardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		User user = (User) session.getAttribute("user");
		
		List<User> users = AdminService.getAllUser();
		List<Transaction> allTransactions = TransactionService.getAllTransactions();
		Account userAccount = UserService.getAccount(user.getId());
		System.out.println(userAccount);
		if (userAccount == null) {
			response.sendRedirect("ErrorController");
			return;
		}
		
		session.setAttribute("users", users);
		List<User> pendingUsers = new ArrayList<>();

		for (User u : users) {
			if (!u.isVerified()) { // use your getter
				pendingUsers.add(u);
			}
		}
		session.setAttribute("pendingUsers", pendingUsers);
		session.setAttribute("allTransactions", allTransactions);
		session.setAttribute("userAccount", userAccount);

		request.getRequestDispatcher("/WEB-INF/views/AdminDashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
