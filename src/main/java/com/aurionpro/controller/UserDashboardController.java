package com.aurionpro.controller;

import java.io.IOException;
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
import com.aurionpro.service.TransactionService;
import com.aurionpro.service.UserService;

@WebServlet("/UserDashboardController")
public class UserDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserDashboardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		Account userAccount = UserService.getAccount(user.getId());
		
		if(userAccount==null) {
			
			response.sendRedirect("ErrorController");  
			return;
		}
		
		List<Transaction> transactions = TransactionService.getUserTransaction(userAccount.getAccountNumber());
		session.setAttribute("userAccount", userAccount);
		session.setAttribute("transactions", transactions);
		request.getRequestDispatcher("/WEB-INF/views/UserDashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
