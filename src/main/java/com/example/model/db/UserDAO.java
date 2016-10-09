package com.example.model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.example.model.User;

public class UserDAO {

private static UserDAO instance;
	
	private UserDAO(){}
	
	public synchronized static UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public Set<User> getAllUsers(){
		Set<User> users = new HashSet<User>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery("SELECT username, password, email FROM users;");
			while(resultSet.next()){
				users.add(new User(	resultSet.getString("username"),
									resultSet.getString("password"),
									resultSet.getString("email")
									));
			}
		} catch (SQLException e) {
			System.out.println("SQL exception!!! Users not load!");
			return users;
		}
		System.out.println("Users loaded successfully");
		return users;
	}
	
	public void saveUser(User user){
		try {
			System.out.println(user.getEmail());
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO users (username, password, email) VALUES (?, ?, ?);");
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setString(3, user.getEmail());
			st.executeUpdate();
			System.out.println("User added successfully");
		} catch (SQLException e) {
			System.out.println("SQL exception. Did not save the user!!!");
			e.printStackTrace();
		}
		
	}

	public void writeNewPassword(String username, String password) {

		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			st.executeUpdate("UPDATE users SET password = " + password +" WHERE username = " + username + " ;");
		} catch (SQLException e) {
			System.out.println("Problems with change password!");
			e.printStackTrace();
		}
	}

	public void writeNewProfilePic(String username, String url) {
		
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			st.executeUpdate("UPDATE user_profile SET profile_pic = " + url +" WHERE Users_username = " + username + " ;");
		} catch (SQLException e) {
			System.out.println("Problems with change password!");
			e.printStackTrace();
		}
	}
	
}
