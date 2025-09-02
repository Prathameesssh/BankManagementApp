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
import com.aurionpro.service.TransactionService;

@WebServlet("/ManageFundsController")
public class ManageFundsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ManageFundsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Account userAccount = (Account) session.getAttribute("userAccount");
		
	    String accountNumber = request.getParameter("accountNumber");
	    String adminAccount = userAccount.getAccountNumber();
	    String actionType = request.getParameter("actionType");
	    String amountParam = request.getParameter("amount");
	    
	    // Parse amount
	    double amount;
	    try {
	        amount = Double.parseDouble(amountParam);
	    } catch (NumberFormatException e) {
	        request.setAttribute("message", "Invalid amount format");
	        request.setAttribute("messageType", "error");
	        response.sendRedirect("AdminDashboardController");
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
	            TransactionService.addTransaction(adminAccount, accountNumber, amount);
	            msg = "Credited ₹" + String.format("%.2f", amount) + " to " + accountNumber;
	        } else {
	            msg = "Failed to credit account " + accountNumber;
	        }
	    } else { // debit
	        success = adminService.debitAccount(accountNumber, amount);
	        if (success) {
	            // record transaction (debit)
	        	TransactionService.addTransaction(accountNumber,adminAccount , amount);
	            msg = "Debited ₹" + String.format("%.2f", amount) + " from " + accountNumber;
	        } else {
	            msg = "Failed to debit account " + accountNumber;
	        }
	    }
	    
	    // Set feedback message
	    request.setAttribute("message", msg);
	    request.setAttribute("messageType", success ? "success" : "error");
	    
	    // Forward back to dashboard
	    response.sendRedirect("AdminDashboardController");
	}


}
