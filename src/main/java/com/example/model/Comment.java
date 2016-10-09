package com.example.model;

public class Comment {

	private String title;
	private String text;
	private String dateAndTime; // time of publishing
	private int likes;
	private int dislikes;
	private String newsTitle; // zaglavieto na nowinata za koqto se otnasq komentara
	private String username; // username na usera koito pishe komentara


	public Comment(String title, String text, String dateAndTime, String newsTitle, String username ) {
		super();
		this.newsTitle = newsTitle;
		this.username = username;
		this.text = text;
		this.title = title;
		this.dateAndTime = dateAndTime;
		this.likes = 0;
		this.dislikes = 0;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		if(newsTitle != null && newsTitle != ""){
			this.newsTitle = newsTitle;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(username != null && username != ""){
			this.username = username;
		}
	}

	public String getText() {
		return text;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		if(dateAndTime != null && dateAndTime != ""){
			this.dateAndTime = dateAndTime;
		}
	}

	public void setText(String text) {
		if(text != null && text != ""){
			this.text = text;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title != null && title != ""){
			this.title = title;
		}
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		if(likes>0){
			this.likes = likes;
		}
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		if(dislikes>0){
			this.dislikes = dislikes;
		}
	}
}
