package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.db.UserDAO;

@Controller
@RequestMapping(value = "/changeProfile")
public class ChangeProfileController {

	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(Model model, HttpServletRequest req, HttpServletResponse res){
		
		if (req.getSession().getAttribute("loggedAs") == null || req.getSession().isNew()) {
			return "login";
		}
		
		String username = (String) req.getSession().getAttribute("loggedAs");
		String newPass = req.getParameter("newPass");
		UserDAO.getInstance().writeNewPassword(username, newPass);
		
		return "profile"; // profile.jsp
	}
	
	@RequestMapping(value = "/changeProfilePicture", method = RequestMethod.POST)
	public String changeProfilePicture(Model model, HttpServletRequest req, HttpServletResponse res){
		
		if (req.getSession().getAttribute("loggedAs") == null || req.getSession().isNew()) {
			return "login";
		}
		
		String username = (String) req.getSession().getAttribute("loggedAs");
		String newProfilePic = req.getParameter("newProfilePic");
		UserDAO.getInstance().writeNewPassword(username, newProfilePic);
		
		return "profile"; 
	}
}
