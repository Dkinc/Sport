package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.UsersManager;

@Controller
@RequestMapping(value = "/Log")
public class LogController {

	 @RequestMapping(value = "/logIn", method = RequestMethod.POST)
	 public String logIn( HttpServletRequest req){
			
		    String username = req.getParameter("username");
			String password = req.getParameter("password");
	
			if(UsersManager.getInstance().validLogin(username, password)){
				req.getSession().setAttribute("loggedAs", username);
				return "index";
			}
			return "loginFailed";

		}
	 
	 @RequestMapping(value = "/logOut", method = RequestMethod.GET)
	 public String logOut(HttpServletRequest req){
			
		    req.getSession().invalidate();
			return "index";
		}
}
