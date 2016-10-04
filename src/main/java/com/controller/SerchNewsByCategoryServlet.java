package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.NewsManager;

@WebServlet("/SerchNewsByCategoryServlet")
public class SerchNewsByCategoryServlet  extends HttpServlet{

private static final long serialVersionUID = 1L;
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String category = request.getParameter("category");
			
			if(NewsManager.getInstance().searchNewsByCategory(category).isEmpty()){
				category = null;
				request.setAttribute("category", null);
			}
			request.setAttribute("category", category);
			RequestDispatcher view = request.getRequestDispatcher("Search.jsp?=" + category);
			view.forward(request, response);
	}
}
