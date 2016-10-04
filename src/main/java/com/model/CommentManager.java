package com.model;

import java.util.HashSet;

import com.model.db.CommentDAO;

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
	
	public synchronized void makeComment(String title, String text, String dateAndTime, String newsTitle, String username ){
		Comment c = new Comment(title, text, dateAndTime, newsTitle, username);
		allComments.add(c);
		CommentDAO.getInstance().addComment(c);
	}
	
	public synchronized HashSet<Comment> searchComment(String title){
		 HashSet<Comment> searchResult = new HashSet<Comment>();
			for (Comment c : allComments) {
				if(c.getTitle().contains(title) || title.contains(c.getTitle())){
					searchResult.add(c);
				}
			}
			return searchResult;
    }
}
