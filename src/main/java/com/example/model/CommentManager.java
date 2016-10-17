package com.example.model;

import java.time.LocalDateTime;
import java.util.HashSet;

import com.example.model.db.CommentDAO;

public class CommentManager {

private static int uniqeCommentId;
private HashSet<Comment> allComments;
	
	
	private static CommentManager instance;
	
	private CommentManager(){
		allComments = new HashSet<Comment>();
		for(Comment c : CommentDAO.getInstance().getAllComments()){
			allComments.add(c);
			if(c.getIdComment() > uniqeCommentId){
				uniqeCommentId = c.getIdComment();
			}
			else{
				uniqeCommentId = 0;
			}
		}
	}
	
	public synchronized static CommentManager getInstance(){
		if(instance == null){
			instance = new CommentManager();
		}
		return instance;
	}
	
	public synchronized void makeComment(String text, LocalDateTime dateAndTime, int idNews, String username){
		Comment c = new Comment(text, dateAndTime, idNews, username);
		CommentDAO.getInstance().addComment(c); // c has setter for idComment !!! And push in allComments with id!!
		allComments.add(c);
		NewsManager.getInstance().getNewsByID(idNews).addComment(c);// push in list of all comments for the news
		
	}
	
	public synchronized void changeCommentAfterLike(int idComment, String username){
		// check if user already like the comment
		for (Comment c : allComments) {
			if(c.getIdComment() == idComment){
				if(c.getUsernames().contains(username)){
					return;
				}
				c.addUsername(username);
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
				c.addUsername(username);
				c.dislikeComment();
			}
		}
		CommentDAO.getInstance().addChangeAfterDislikeComment(idComment);
		CommentDAO.getInstance().addUserDislike(idComment, username);
	}
	
	 public synchronized Comment getCommentByID(int id){
			for (Comment c : allComments) {
				if(c.getIdComment() == id){
					return c;
				}
			}
			return null;
	 }
}
