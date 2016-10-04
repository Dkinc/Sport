package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.db.UserDAO;
@WebServlet("/ChangeProfilePictureServlet")
public class ChangeProfilePictureServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			if (request.getSession().getAttribute("loggedAs") == null || request.getSession().isNew()) {
				response.sendRedirect("login.jsp");
			}
			
			String username = (String) request.getSession().getAttribute("loggedAs");
			String newProfilePic = request.getParameter("newProfilePic");
			UserDAO.getInstance().writeNewProfilePic(username, newProfilePic); 
	 }
}
