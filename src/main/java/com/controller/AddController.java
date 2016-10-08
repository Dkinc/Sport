package com.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.Admin;
import com.model.CommentManager;

@Controller
@RequestMapping(value = "/add")
public class AddController {

	@RequestMapping(value = "/addNews", method = RequestMethod.POST)
	public String addNews(Model model, HttpServletRequest req){
	
		if (req.getSession().getAttribute("loggedAs") == null || req.getSession().isNew()) {
			return "login";
		}
		String title = req.getParameter("title");
		String text = req.getParameter("text");
		String category = req.getParameter("category");
		String picturesURL = req.getParameter("picturesURL");
		String videoURL = req.getParameter("videoURL");
		boolean validation = false;
		if (title != null && text != null && category != null && picturesURL != null  && videoURL != null) {
			if (title != "" && text != "" && category != "" && picturesURL != "" && videoURL != "") {
				validation = true;
			}
		}
		if (validation) {
			Admin.getInstance().addNews(title, text, category, picturesURL, videoURL);
			return "index"; 
			
		}
	    return "AddNews"; // AdminAddNews
		
	}
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public String addComment(Model model, HttpServletRequest req){
	
		if (req.getSession().getAttribute("loggedAs") == null || req.getSession().isNew()) {
			return "login";
		}
		String title = req.getParameter("title");
		String text = req.getParameter("text");
		/*
		 * ��� �� �� ����� dateAndTime - ������� �� ����������� ???!!!
		 * ��� �� ����� ���������� �� �������� �� ����� ����� ���� ��������
		 */
	    
	//	String newsTitle ??????? 
		String username = (String) req.getSession().getAttribute("loggedAs");
		
		boolean validation = false;
		if(title != null && text != null && title != "" && text != ""){
				validation = true;	
		}
		if (validation) {
			CommentManager.getInstance().makeComment(title, text, LocalDateTime.now().toString(), "?????????????????", username);
			return "index";
		} 
		return "AddComment";
		
	}
}
