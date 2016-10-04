package com.model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import com.model.Comment;
import com.model.News;

public class CommentDAO {

	 private static CommentDAO instance;
		
		private CommentDAO(){}
		
		public synchronized static CommentDAO getInstance(){
			if(instance == null){
				instance = new CommentDAO();
			}
			return instance;
		}
		/*
		 * Vsichki komentari za vsichki novini .Naredbata e po wreme na publikuwane.	
		 */
		public HashSet<Comment> getAllComments(){
			HashSet<Comment> comments = new HashSet<Comment>();
			try {
				Statement st = DBManager.getInstance().getConnection().createStatement();
				ResultSet resultSet = st.executeQuery("SELECT C.title_of_comment, C.text, C.date_and_time, U.username, N.title"
						+ " FROM comments C JOIN users U ON C.Users_idUsers = U.idUsers"
						+ "JOIN news N ON C.News_idNews = N.idNews"
						+ " ORDER BY date_and_time Desc;");
				while(resultSet.next()){
					comments.add(new Comment(	resultSet.getString("title_of_comment"),
												resultSet.getString("text"),
												resultSet.getString("date_and_time"),
												resultSet.getString("title"),
												resultSet.getString("username")
											));
				}
			} catch (SQLException e) {
				System.out.println("cannot make statement in getAllComments!!!");
				return comments;
			}
			System.out.println("Comments loaded successfully");
			return comments;
		}
		
		/*
		 * Vsichki komentari za konkretna novina .Naredbata e po wreme na publikuwane.
		 */
		public HashSet<Comment> getAllCommentsForNews(News news){
			HashSet<Comment> comments = new HashSet<Comment>();
			for (Comment c : instance.getAllComments()) {
				if(c.getNewsTitle().equals(news.getTitle())){
					comments.add(c);
				}
			}
			return comments;
		}
		
		public void addComment(Comment c){
			try {
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO comments (title_of_comment, text,"
						+ " date_and_time, title, username) VALUES (?,?,?,?,?);");
				
				st.setString(1, c.getTitle());
				st.setString(2, c.getText());
				st.setString(3, c.getDateAndTime());
				st.setString(4, c.getNewsTitle());
				st.setString(5, c.getUsername());
				
				
				st.executeUpdate();
				System.out.println("Comment added successfully");
			} catch (SQLException e) {
				System.out.println("did not save the comment");
				e.printStackTrace();
			}		
		}	
}
