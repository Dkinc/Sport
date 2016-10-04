package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Admin;

@WebServlet("/AddNewsByAdminServlet")
public class AddNewsByAdminServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("loggedAs") == null || request.getSession().isNew()) {
			response.sendRedirect("login.jsp");
		}
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");
		String picturesURL = request.getParameter("picturesURL");
		String videoURL = request.getParameter("videoURL");
		boolean validation = false;
		if (title != null && text != null && category != null && picturesURL != null  && videoURL != null) {
			if (title != "" && text != "" && category != "" && picturesURL != "" && videoURL != "") {
				validation = true;
			}
		}
		if (validation) {
			Admin.getInstance().addNews(title, text, category, picturesURL, videoURL);
			RequestDispatcher view = request.getRequestDispatcher("indexAdmin.jsp");
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("AdminAddNews.jsp");
			view.forward(request, response);

		}
	}
}
