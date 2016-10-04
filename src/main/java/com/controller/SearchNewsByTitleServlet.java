package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.NewsManager;

@WebServlet("/SearchNewsByTitleServlet")
public class SearchNewsByTitleServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String title = request.getParameter("title");
			
			if(NewsManager.getInstance().searchNewsByTitle(title).isEmpty()){
				title = null;
				request.setAttribute("title", null);
			}
			request.setAttribute("title", title);
			RequestDispatcher view = request.getRequestDispatcher("Search.jsp?=" + title);
			view.forward(request, response);
	}
}
