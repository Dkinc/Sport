package com.example.controller;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.model.Comment;
import com.example.model.CommentManager;

@Controller
public class CommentController {

	
	@RequestMapping(value="/addcomment" , method=RequestMethod.POST)
	public String addComment( Model model, @ModelAttribute Comment c) {
		CommentManager.getInstance().makeComment(c.getText(), c.getDateAndTime(), c.getNewsTitle(), c.getUsername());
		return "addcomment";
	}
	
	@RequestMapping(value="/likecomment" , method=RequestMethod.POST)
	public String likeComment( Model model, @ModelAttribute int idComment, HttpSession s) {// idComment is hidden field in spring-form 
		CommentManager.getInstance().changeCommentAfterLike(idComment, s.getAttribute("loggedAs").toString());
		return "comments";// refresh after like
	}
	
	@RequestMapping(value="/dislikecomment" , method=RequestMethod.POST)
	public String dislikeComment( Model model, @ModelAttribute int idComment, HttpSession s) {// also
		CommentManager.getInstance().changeCommentAfterDislike(idComment, s.getAttribute("loggedAs").toString());
		return "comments";// refresh after dislike
	}
}
