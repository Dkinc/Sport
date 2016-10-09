package com.example.model;

import java.util.concurrent.ConcurrentHashMap;

import com.example.model.db.UserDAO;

public class AdminManager {

	
	private static AdminManager instance;
	
	private ConcurrentHashMap<String, User> registerredUsers;//username -> user (admin is included)
	
	private AdminManager(){
		registerredUsers = new ConcurrentHashMap<String, User>();
		for(User u : UserDAO.getInstance().getAllUsers()){
			registerredUsers.put(u.getUsername(), u);
		}
	}
	
	public synchronized static AdminManager getInstance(){
		if(instance == null){
			instance = new AdminManager();
		}
		return instance;
	}

}
