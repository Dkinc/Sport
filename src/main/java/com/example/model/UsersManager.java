package com.example.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;

import com.example.model.db.UserDAO;

public class UsersManager {

	private ConcurrentHashMap<String, User> registerredUsers;// username -> user
	private static UsersManager instance;

	private UsersManager() {
		registerredUsers = new ConcurrentHashMap<String, User>();
		for (User u : UserDAO.getInstance().getAllUsers()) {
			registerredUsers.put(u.getUsername(), u);
		}
	}

	public synchronized static UsersManager getInstance() {
		if (instance == null) {
			instance = new UsersManager();
		}
		return instance;
	}

	public User getUser(String username) {
		return registerredUsers.get(username);
	}

	public boolean validLogin(String username, String password) {
		String hashedPassword = passwordHash(password);
		if (!registerredUsers.containsKey(username)) {
			return false;
		}
		return registerredUsers.get(username).getPassword().equals(hashedPassword);
	}

	public void regUser(String username, String password, String email) {
		String hashedPassword = passwordHash(password);
		if (!registerredUsers.containsKey(username)) {

			User user = new User(username, hashedPassword, email);
			System.out
					.println("controller : " + user.getUsername() + "  " + user.getPassword() + "  " + user.getEmail());
			registerredUsers.put(username, user);
			UserDAO.getInstance().saveUser(user);
		} else {
			System.out.println("Username already exists");
		}
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

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return sb.toString();
	}
}
