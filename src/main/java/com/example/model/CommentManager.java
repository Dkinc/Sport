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
		NewsManager.getInstance().getNewsByTitle(newsTitle).getAllCommentsForNews().add(c);
	}
	
	public synchronized void changeCommentAfterLike(int idComment, String username){
		// check if user already like the comment
		for (Comment c : allComments) {
			if(c.getIdComment() == idComment){
				if(c.getUsernames().contains(username)){
					return;
				}
				c.getUsernames().add(username);
				c.likeComment();
			}
		}
		CommentDAO.getInstance().addChangeAfterLikeComment(idComment);
		CommentDAO.getInstance().addUserLike(idComment, username);
	}
	
	public synchronized void changeCommentAfterDislike(int idComment, String username){
		// check if user already dislike the comment
		for (Comment c : allComments) {
			if(c.getIdComment() == idComment){
				if(c.getUsernames().contains(username)){
					return;
				}
				c.getUsernames().add(username);
				c.dislikeComment();
			}
		}
		CommentDAO.getInstance().addChangeAfterDislikeComment(idComment);
		CommentDAO.getInstance().addUserDislike(idComment, username);
	}
}
