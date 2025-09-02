package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.AdminService;

/**
 * Servlet implementation class FreezeAccountController
 */
@WebServlet("/FreezeAccountController")
public class FreezeAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreezeAccountController() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Read form data
	    String accountNumber = request.getParameter("accountNumber");
	    String action = request.getParameter("action");
	    
	    try {
	        // Initialize service (assuming you have an AccountService)
	        AdminService adminService = new AdminService();
	        
	        boolean success = false;
	        String message = "";
	        String messageType = "";
	        
	        if ("freeze".equals(action)) {
	            success = adminService.changeFreezeStatus(accountNumber, true);
	            if (success) {
	                message = "Account " + accountNumber + " has been frozen successfully.";
	                messageType = "success";
	            } else {
	                message = "Failed to freeze account " + accountNumber + ". Account not found or already frozen.";
	                messageType = "error";
	            }
	        } else if ("unfreeze".equals(action)) {
	            success = adminService.changeFreezeStatus(accountNumber,false);
	            if (success) {
	                message = "Account " + accountNumber + " has been unfrozen successfully.";
	                messageType = "success";
	            } else {
	                message = "Failed to unfreeze account " + accountNumber + ". Account not found or already active.";
	                messageType = "error";
	            }
	        } else {
	            message = "Invalid action specified.";
	            messageType = "error";
	        }
	        
	        // Set message attributes
	        request.setAttribute("message", message);
	        request.setAttribute("messageType", messageType);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("message", "An error occurred: " + e.getMessage());
	        request.setAttribute("messageType", "error");
	    }
	    
	    // Redirect back to admin dashboard
	    response.sendRedirect("AdminDashboardController");
	}

}
