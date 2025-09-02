package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.UserService;
import com.aurionpro.utils.UserAuthentication;

@WebServlet("/RegisterPageController")
public class RegisterPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterPageController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().println("RegisterPageController is working!");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			request.setAttribute("error", "Invalid mobile or Aadhar number.");
			e.printStackTrace();
		}

		// ✅ Validate with UserAuthentication
		if (!UserAuthentication.validate(name, address, email, password, mobile, aadharNo)) {
			request.setAttribute("error", "Invalid input. Please check your details.");
			request.getRequestDispatcher("/WEB-INF/views/RegisterPage.jsp").forward(request, response);
			return;
		}

		UserService.addUser(name, address, email, password, mobile, aadharNo);

		request.setAttribute("success", "Registration successful. Please login.");
		request.getRequestDispatcher("/WEB-INF/views/RegisterPage.jsp").forward(request, response);
	}

}
