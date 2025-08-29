package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.UserService;

@WebServlet("/RegisterPageController")
public class RegisterPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterPageController() {
        super();  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.getWriter().println("RegisterPageController is working!");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobileStr = request.getParameter("mobile");
		String aadharStr = request.getParameter("aadharNo");

		long mobile = 0L;
		long aadharNo = 0L;

		try {
		    mobile = Long.parseLong(mobileStr);
		    aadharNo = Long.parseLong(aadharStr);
		} catch (NumberFormatException e) {
			
		    e.printStackTrace();
		    
		}
		
		UserService.addUser(name,address,email,password,mobile,aadharNo);
		
		request.getRequestDispatcher("/WEB-INF/views/LoginPage.jsp").forward(request, response);	
	}

}
