package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.News;
import com.model.NewsManager;

@WebServlet("/GetNewsServlet")
public class GetNewsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		News news = NewsManager.getInstance().getNewsByTitle(request.getParameter("title"));
		request.setAttribute("title", news.getTitle());
		request.setAttribute("text", news.getText());
		request.setAttribute("category", news.getCategory());
		request.setAttribute("picturesURL", news.getPicturesURL());
		request.setAttribute("videoURL", news.getVideoURL());
		RequestDispatcher view = request.getRequestDispatcher("ShowNews.jsp");
		view.forward(request, response);
	}
}
