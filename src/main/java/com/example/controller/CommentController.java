package com.example.controller;



import java.time.LocalDateTime;

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
		System.out.println("TITLE = " + c.getNewsTitle());
		CommentManager.getInstance().makeComment(c.getText(), LocalDateTime.now(), c.getNewsTitle(), s.getAttribute("loggedAs").toString());
		News news = NewsManager.getInstance().getNewsByTitle(c.getNewsTitle());
		return "//" + Integer.toString(news.getIdNews());
	}
	
	@RequestMapping(value="/likeComment" , method=RequestMethod.POST)
	public String likeComment( Model model, @ModelAttribute int idComment, HttpSession s) {// idComment is hidden field in spring-form 
		CommentManager.getInstance().changeCommentAfterLike(idComment, s.getAttribute("loggedAs").toString());
		return "comments";// refresh after like
	}
	
	@RequestMapping(value="/dislikeComment" , method=RequestMethod.POST)
	public String dislikeComment( Model model, @ModelAttribute int idComment, HttpSession s) {// also
		CommentManager.getInstance().changeCommentAfterDislike(idComment, s.getAttribute("loggedAs").toString());
		return "comments";// refresh after dislike
	}
}
