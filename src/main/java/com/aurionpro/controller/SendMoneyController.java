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

@WebServlet("/SendMoneyController")
public class SendMoneyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SendMoneyController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Account userAccount = (Account) session.getAttribute("userAccount");

		String fromAccount = userAccount.getAccountNumber();
		String toAccount = request.getParameter("toAccount");
		String amount = request.getParameter("amount");
		try {
			double transferAmount = Double.parseDouble(amount);

			// 1. Check sender balance
			boolean hasBalance = AuthBalance.checkSenderBalance(userAccount, transferAmount);
			if (!hasBalance) {
				request.setAttribute("error", "Insufficient balance or account is frozen.");
				request.getRequestDispatcher("/UserDashboard.jsp").forward(request, response);
				return;
			}

			// 2. Transfer money
			boolean transferSuccess = TransactionService.transferMoney(fromAccount, toAccount, transferAmount);
			if (transferSuccess) {
				request.setAttribute("success", "₹" + transferAmount + " sent successfully to account " + toAccount);
			} else {
				request.setAttribute("error", "Transaction failed. Please check details and try again.");
			}

		} catch (NumberFormatException e) {
			request.setAttribute("error", "Invalid amount entered.");
		}
		response.sendRedirect("UserDashboardController");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
