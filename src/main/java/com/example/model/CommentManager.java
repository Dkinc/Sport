package com.example.model;

import java.time.LocalDateTime;
import java.util.HashSet;

import com.example.model.db.CommentDAO;

public class CommentManager {

private HashSet<Comment> allComments;
	
	
	private static CommentManager instance;
	
	private CommentManager(){
		allComments = new HashSet<Comment>();
		for(Comment c : CommentDAO.getInstance().getAllComments()){
			allComments.add(c);
		}
	}
	
	public synchronized static CommentManager getInstance(){
		if(instance == null){
			instance = new CommentManager();
		}
		return instance;
	}
	
	public synchronized void makeComment(String text, LocalDateTime dateAndTime, String newsTitle, String username){
		Comment c = new Comment(text, dateAndTime, newsTitle, username);
		allComments.add(c);
		CommentDAO.getInstance().addComment(c);
	}
	
}
