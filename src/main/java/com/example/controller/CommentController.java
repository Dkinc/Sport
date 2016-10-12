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

	@RequestMapping(value="/addcomment", method=RequestMethod.GET)
	public String getAddComment(HttpSession s, Model model) {
		model.addAttribute("comment", new Comment());
		if(s.getAttribute("loggedAs")!= null && !s.isNew()){
			return "addcomment";
		}
			s.invalidate();
		return "login";
	}
	
	@RequestMapping(value="/addcomment" , method=RequestMethod.POST)
	public String addComment( Model model, @ModelAttribute Comment c) {
		CommentManager.getInstance().makeComment(c.getText(), c.getDateAndTime(), c.getNewsTitle(), c.getUsername());
		return "addcomment";
	}
}
