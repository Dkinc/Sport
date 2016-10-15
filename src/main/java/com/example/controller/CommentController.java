package com.example.controller;



import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.model.Comment;
import com.example.model.CommentManager;
import com.example.model.News;
import com.example.model.NewsManager;

@Controller
public class CommentController {

	
	@RequestMapping(value="/addComment" , method=RequestMethod.POST)
	public String addComment( Model model, @ModelAttribute Comment c, HttpSession s) {
		CommentManager.getInstance().makeComment(c.getText(), LocalDateTime.now(), c.getIdNews(), s.getAttribute("loggedAs").toString());
		News news = NewsManager.getInstance().getNewsByID(c.getIdNews());
		
		return "redirect:/" + Integer.toString(news.getIdNews());
	}
	
	@RequestMapping(value="/likeComment" , method=RequestMethod.POST)
	public String likeComment(HttpServletRequest req, HttpSession s) {// idComment is hidden field in spring-form 
		System.out.println("ID = " + req.getAttribute("id"));
		int id = (int) req.getAttribute("id");
		CommentManager.getInstance().changeCommentAfterLike(id, s.getAttribute("loggedAs").toString());
		News news = NewsManager.getInstance().getNewsByID(CommentManager.getInstance().getCommentByID(id).getIdNews());
		return "redirect:/" + Integer.toString(news.getIdNews());
	}
	
	@RequestMapping(value="/dislikeComment" , method=RequestMethod.POST)
	public String dislikeComment( Model model, @ModelAttribute int id, HttpSession s) {// also
		CommentManager.getInstance().changeCommentAfterDislike(id, s.getAttribute("loggedAs").toString());
		News news = NewsManager.getInstance().getNewsByID(CommentManager.getInstance().getCommentByID(id).getIdNews());
		return "redirect:/" + Integer.toString(news.getIdNews());
	}
}
