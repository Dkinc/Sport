package com.example.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Comment {

	private int idComment;
	private String text;
	private LocalDateTime dateAndTime; 
	private int likes;
	private int dislikes;
	private int idNews;
	private String username; // username of user that  write the comment
	private List<String> usernames;// username of users that likes/dislikes the comment

	public Comment(){}

	public Comment(int id, String text, LocalDateTime dateAndTime, int idNews, String username ) {
		
		this.setIdNews(idNews);
		this.username = username;
		this.text = text;
		this.dateAndTime = dateAndTime;
		this.usernames = new ArrayList<String>();
		this.likes = 0;
		this.dislikes = 0;
	}

	public List<String> getUsernames() {
		return Collections.unmodifiableList(usernames);
	}
	
	public void addUsername(String username){
		usernames.add(username);
	}

	public void likeComment(){
		this.likes++;
	}
	
	public void dislikeComment(){
		this.dislikes++;
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
	
	public int getIdComment() {
		return idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}

}
