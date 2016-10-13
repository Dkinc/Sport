package com.example.model;

import java.time.LocalDateTime;

public class Comment {

	
	private String text;
	private LocalDateTime dateAndTime; 
	private int likes = 0;
	private int dislikes = 0;
	private String newsTitle;
	private String username; 

	public Comment(){}

	public Comment(String text, LocalDateTime dateAndTime, String newsTitle, String username ) {
		super();
		this.newsTitle = newsTitle;
		this.username = username;
		this.text = text;
		this.dateAndTime = dateAndTime;
	}

	public void likeComment(){
		this.likes++;
	}
	
	public void disLikeComment(){
		this.dislikes++;
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

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		if(dateAndTime != null){
			this.dateAndTime = dateAndTime;
		}
	}

	public void setText(String text) {
		if(text != null && text != ""){
			this.text = text;
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
