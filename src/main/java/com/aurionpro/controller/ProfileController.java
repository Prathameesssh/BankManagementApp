package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.model.User;
import com.aurionpro.service.UserService;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userIdStr = request.getParameter("userId");
		if (userIdStr != null) {
			int userId = Integer.parseInt(userIdStr);

			// Fetch user from DB (pseudo-code, replace with your DAO/service)
			User user = UserService.getUserById(userId);

			if (user != null) {
				request.setAttribute("profileUser", user);
				request.getRequestDispatcher("/WEB-INF/views/Profile.jsp").forward(request, response);
			} else {
				response.getWriter().println("User not found");
			}
		} else {
			response.getWriter().println("Invalid request, userId missing");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
