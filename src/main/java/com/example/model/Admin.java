package com.example.model;

public class Admin extends User{

private static Admin instance;
	
	public Admin(){}
	
	public Admin(String username, String password, String email) {
		super(username, password, email);	
	}
	
	public synchronized static Admin getInstance(){
		if(instance == null){
			instance = new Admin();
		}
		return instance;
	}

	public void addNews(String title, String text, String category, String picturesURL, String videoURL){
		NewsManager.getInstance().makeNews(title, text, category, picturesURL, videoURL);
	}
	
	@Override
	public boolean hasAdministrationFunctionality() {
		return true;
	}
}
