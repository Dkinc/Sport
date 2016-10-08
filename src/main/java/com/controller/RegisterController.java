package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.UsersManager;

@Controller
@RequestMapping(value = "/Register")
public class RegisterController {

	 @RequestMapping(method = RequestMethod.POST)
	 public String makeRegistration( HttpServletRequest req){
	 
		    String username = req.getParameter("username");
			String password = req.getParameter("password");
			String password2 = req.getParameter("password2");
			String email = req.getParameter("email");
			boolean valid = false;
			if (username != null && password != null && password2 != null && email != null) {
				if (username != "" && password != "" && password.equals(password2)) {
					if (isValidEmailAddress(email)) {
						valid = true;
					}
				}
			}
			if (valid) {
				UsersManager.getInstance().regUser(username, password2, email);				
				return "index";
			}
			return "register";
	
	 }
	 
	 public boolean isValidEmailAddress(String email) {
			String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
			java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
			java.util.regex.Matcher m = p.matcher(email);
			return m.matches();
		}
}
