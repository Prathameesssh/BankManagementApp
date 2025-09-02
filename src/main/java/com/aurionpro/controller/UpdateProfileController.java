package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.model.User;
import com.aurionpro.service.UserService;
import com.aurionpro.utils.UserAuthentication;

/**
 * Servlet implementation class UpdateProfileController
 */
@WebServlet("/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileController() {
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
	    // Read form data from request parameters
	    String fullName = request.getParameter("fullName");
	    String address = request.getParameter("address");
	    String email = request.getParameter("email");
	    String mobile = request.getParameter("mobile");
	    
	    UserAuthentication userAuthentication = new UserAuthentication();
	    boolean isDataCorrect = userAuthentication.validate(fullName, address, email, Long.parseLong(mobile));
	    
	    if(!isDataCorrect) {
	    	 request.setAttribute("message", "Failed to update profile. Please try again.");
	            request.setAttribute("messageType", "error");
	            response.sendRedirect("UserDashboardController");
	            return;
	    }
	    // Get current user from session
	    User user = (User) request.getSession().getAttribute("user");
	    
	    if (user != null) {
	        // Update user object with new values
	        user.setName(fullName);
	        user.setAddress(address);
	        user.setEmail(email);
	        user.setMobile(Long.parseLong(mobile)); // Convert String to long
	        
	        // Call service/DAO to update user in database
	        UserService userService = new UserService();
	        boolean updateSuccess = userService.updateUser(user);
	        
	        if (updateSuccess) {
	            // Update session with modified user object
	            request.getSession().setAttribute("user", user);
	            
	            // Set success message
	            request.setAttribute("message", "Profile updated successfully!");
	            request.setAttribute("messageType", "success");
	        } else {
	            // Set error message
	            request.setAttribute("message", "Failed to update profile. Please try again.");
	            request.setAttribute("messageType", "error");
	        }
	    } else {
	        // User not found in session
	        request.setAttribute("message", "Session expired. Please login again.");
	        request.setAttribute("messageType", "error");
	    }
	    
	    // Forward back to dashboard
	    response.sendRedirect("UserDashboardController");
	}


}
