package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.CommentManager;

@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	 @Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			if (request.getSession().getAttribute("loggedAs") == null || request.getSession().isNew()) {
				response.sendRedirect("login.jsp");
			}
			String title = request.getParameter("title");
			String text = request.getParameter("text");
			/*
			 * ��� �� �� ����� dateAndTime - ������� �� ����������� ???!!!
			 * ��� �� ����� ���������� �� �������� �� ����� ����� ���� ��������
			 */
		    
		//	String newsTitle ???????
			String username = (String) request.getSession().getAttribute("loggedAs");
			
			boolean validation = false;
			if(title != null && text != null && title != "" && text != ""){
					validation = true;	
			}
			if (validation) {
				CommentManager.getInstance().makeComment(title, text, "???????????", "?????????????????", username);
				RequestDispatcher view = request.getRequestDispatcher("index.jsp");
				view.forward(request, response);
			} else {
				RequestDispatcher view = request.getRequestDispatcher("AddComment.jsp");
				view.forward(request, response);

			}
		}
}
