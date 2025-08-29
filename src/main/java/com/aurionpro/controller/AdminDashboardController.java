package com.aurionpro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Transaction;
import com.aurionpro.model.User;
import com.aurionpro.service.AdminService;
import com.aurionpro.service.TransactionService;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> pendingUsers = AdminService.getPendingUser();
		List<Transaction> allTransactions = TransactionService.getAllTransactions();
		HttpSession session = request.getSession(false);
		session.setAttribute("pendingUsers", pendingUsers);
		System.out.println(pendingUsers);
		session.setAttribute("allTransactions",allTransactions);
		
		request.getRequestDispatcher("/WEB-INF/views/AdminDashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
