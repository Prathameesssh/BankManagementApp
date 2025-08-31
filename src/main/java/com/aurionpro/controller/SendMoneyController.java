package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Account;
import com.aurionpro.service.TransactionService;
import com.aurionpro.utils.AuthBalance;

/**
 * Servlet implementation class SendMoneyController
 */
@WebServlet("/SendMoneyController")
public class SendMoneyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SendMoneyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Account userAccount = (Account) session.getAttribute("userAccount");
		
		String fromAccount = userAccount.getAccountNumber();
		String toAccount = request.getParameter("toAccount");
		String amount = request.getParameter("amount");
		double transferAmount = Double.parseDouble(amount);
		
		boolean checkSenderBalance = AuthBalance.checkSenderBalance(userAccount, transferAmount);
		if(!checkSenderBalance) {
			response.sendRedirect("UserDashboardController");
		}
		boolean transferMoney = TransactionService.transferMoney(fromAccount,toAccount,transferAmount);
		response.sendRedirect("UserDashboardController");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
