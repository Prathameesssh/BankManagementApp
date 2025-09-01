package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.AdminService;
import com.aurionpro.service.TransactionService;

/**
 * Servlet implementation class ManageFundsController
 */
@WebServlet("/ManageFundsController")
public class ManageFundsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageFundsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Read form parameters
	    String accountNumber = request.getParameter("accountNumber");
	    String actionType = request.getParameter("actionType");  // "credit" or "debit"
	    String amountParam = request.getParameter("amount");
	    
	    // Parse amount
	    double amount;
	    try {
	        amount = Double.parseDouble(amountParam);
	    } catch (NumberFormatException e) {
	        request.setAttribute("message", "Invalid amount format");
	        request.setAttribute("messageType", "error");
	        request.getRequestDispatcher("/WEB-INF/views/AdminDashboard.jsp")
	               .forward(request, response);
	        return;
	    }
	    
	    AdminService adminService = new AdminService();
	    
	    boolean success = false;
	    String msg;
	    
	    if ("credit".equals(actionType)) {
	        // Credit the account
	        success = adminService.creditAccount(accountNumber, amount);
	        if (success) {
	            // record transaction (credit)
	            TransactionService.addTransaction(accountNumber, "Integro Bank", amount);
	            msg = "Credited ₹" + String.format("%.2f", amount) + " to " + accountNumber;
	        } else {
	            msg = "Failed to credit account " + accountNumber;
	        }
	    } else { // debit
	        success = adminService.debitAccount(accountNumber, amount);
	        if (success) {
	            // record transaction (debit)
	        	TransactionService.addTransaction("Integro Bank", accountNumber, amount);
	            msg = "Debited ₹" + String.format("%.2f", amount) + " from " + accountNumber;
	        } else {
	            msg = "Failed to debit account " + accountNumber;
	        }
	    }
	    
	    // Set feedback message
	    request.setAttribute("message", msg);
	    request.setAttribute("messageType", success ? "success" : "error");
	    
	    // Forward back to dashboard
	    request.getRequestDispatcher("/WEB-INF/views/AdminDashboard.jsp")
	           .forward(request, response);
	}


}
