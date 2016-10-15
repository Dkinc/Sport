package com.example.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static DBManager instance;
	private Connection connection;
	
	private static final String DB_IP = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "sportaldb";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "153405405153Mm#";
	private static final String URL = "jdbc:mysql://"+DB_IP+":"+DB_PORT+"/"+DB_NAME;
	
	private DBManager(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed Driver");
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println("Failed connection");
			
			e.printStackTrace();
		}
	}
	
	synchronized static DBManager getInstance(){
		if(instance == null){
			instance = new DBManager();
		}
		return instance;
	}
	
	Connection getConnection() {
		return connection;
	}
}
