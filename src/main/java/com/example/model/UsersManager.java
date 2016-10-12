package com.example.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.example.model.db.UserDAO;

public class UsersManager {
	
	private ConcurrentHashMap<String, User> registerredUsers;//username -> user
	private static UsersManager instance;
	private UsersManager(){
		registerredUsers = new ConcurrentHashMap<String, User>();
		for(User u : UserDAO.getInstance().getAllUsers()){
			registerredUsers.put(u.getUsername(), u);
		}
	}
	
	public synchronized static UsersManager getInstance(){
		if(instance == null){
			instance = new UsersManager();
		}
		return instance;
	}
	
	public User getUser(String username){
		return registerredUsers.get(username);
	}
	
	public boolean validLogin(String username, String password) {
		String hashedPassword = passwordHash(password);
		if (!registerredUsers.containsKey(username)) {
			return false;
		}
		return registerredUsers.get(username).getPassword().equals(hashedPassword);
	}
	
	public boolean regUser(String username, String password, String email){
		if(!registerredUsers.containsKey(username)){
			if(username != null && username != "" && password.length() > 5 && password.length() < 45 && isValidEmailAddress(email)){			
				String hashedPassword = passwordHash(password);
				User user = new User(username, hashedPassword , email);
				System.out.println("controller : " + user.getUsername() + "  " + user.getPassword() + "  "  + user.getEmail());
				registerredUsers.put(username, user);
				UserDAO.getInstance().saveUser(user);
				return true;
			}
		}
		else{
			System.out.println("Username already exists");
			return false;
		}
		return false;
	}
	
	private static String passwordHash(String password) {
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] b = md.digest();
			for (byte b1 : b) {
				String k = Integer.toHexString(b1 & 0xff).toString();
				if (k.length() == 1) {
					k = "0" + k;
				}
				sb.append(k);
			}
			System.out.println(sb.toString());

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return sb.toString();
	}
	
	private boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
}
