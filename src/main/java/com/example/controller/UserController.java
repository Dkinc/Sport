package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.User;
import com.example.model.UsersManager;

@Controller
public class UserController {

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getRegister(Model model ) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@RequestMapping(value="/registerFailed", method=RequestMethod.GET)
	public String getRegisterFailed(Model model ) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@RequestMapping(value="/registerFailed", method=RequestMethod.POST)
	public String registerf(@ModelAttribute User u) {
		if(UsersManager.getInstance().regUser(u.getUsername().toLowerCase(), u.getPassword(), u.getEmail())){
			return "index";			
		}
		return "registerFailed";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute User u) {
		if(UsersManager.getInstance().regUser(u.getUsername().toLowerCase(), u.getPassword(), u.getEmail())){
			return "index";			
		}
		return "registerFailed";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLogin(Model model ) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value="/loginFailed", method=RequestMethod.GET)
	public String getLoginFiled(Model model ) {
		model.addAttribute("user", new User());
		return "loginFailed";
	}
	
	@RequestMapping(value="/loginFailed", method=RequestMethod.POST)
	public String loginF(@ModelAttribute User u , HttpSession s) {
		if(UsersManager.getInstance().validLogin(u.getUsername().toLowerCase(), u.getPassword())){
			s.setAttribute("loggedAs", u.getUsername());
			
			return "redirect:index";
		}
		return "loginFailed";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute User u , HttpSession s ) {
		if(UsersManager.getInstance().validLogin(u.getUsername().toLowerCase(), u.getPassword())){
			s.setAttribute("loggedAs", u.getUsername());
			return "redirect:index";
		}
		return "loginFailed";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession s) {
		s.invalidate();
		return "redirect:index";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String getProfile(Model model, HttpSession s ) {
		model.addAttribute("user", s.getAttribute("loggedAs"));
		return "profile";
	}
	
	@RequestMapping(value="/changePass", method=RequestMethod.POST)
	public String changePass(Model model, HttpServletRequest req , HttpSession s) {
		String oldPass = (String) req.getParameter("oldpass");
		String newPass1 = (String) req.getParameter("newpass1");
		String newPass2 = (String) req.getParameter("newpass2");
		String username = (String) s.getAttribute("loggedAs");
		if(UsersManager.getInstance().validLogin(username.toLowerCase(), oldPass)){
			if(newPass1.equals(newPass2)){
				UsersManager.getInstance().getUser(username).changePassword(newPass1);
			}
		}
		
		return "profile";
	}
}
