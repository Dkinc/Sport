package com.example.model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashSet;

import com.example.model.Comment;
import com.example.model.News;

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
				ResultSet resultSet = st.executeQuery("SELECT C.text, C.date_and_time, U.username, N.title"
						+ " FROM comments C JOIN users U ON C.Users_idUsers = U.idUsers"
						+ "JOIN news N ON C.News_idNews = N.idNews"
						+ " ORDER BY date_and_time Desc;");
				while(resultSet.next()){
					comments.add(new Comment(	resultSet.getString("text"),
												resultSet.getTimestamp("date_and_time").toLocalDateTime(),
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
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO comments ( text,"
						+ " date_and_time, title, username, number_of_likes, number_of_dislikes) VALUES (?,?,?,?,?,?,?);");
				
				Calendar cal = Calendar.getInstance();
				
				st.setString(1, c.getText());
				st.setDate(2, new java.sql.Date(cal.getTimeInMillis()));
				st.setString(3, c.getNewsTitle());
				st.setString(4, c.getUsername());
				st.setInt(5, c.getLikes());
				st.setInt(6, c.getDislikes());
				
				st.executeUpdate();
				System.out.println("Comment added successfully");
			} catch (SQLException e) {
				System.out.println("did not save the comment");
				e.printStackTrace();
			}		
		}	
}
