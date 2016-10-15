package com.example.model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashSet;

import com.example.model.Comment;

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
				ResultSet resultSet = st.executeQuery("SELECT C.idComments, C.text, C.date_and_time, U.username, N.idNews"
						+ " FROM comments C JOIN users U ON C.Users_idUsers = U.idUsers"
						+ "JOIN news N ON C.News_idNews = N.idNews"
						+ " ORDER BY date_and_time Desc;");
				while(resultSet.next()){
					
					Comment c = new Comment(	resultSet.getString("text"),
												resultSet.getTimestamp("date_and_time").toLocalDateTime(),
												resultSet.getInt("idNews"),
												resultSet.getString("username")
											);
					
					c.setIdComment(resultSet.getInt("idComments"));
					comments.add(c);
				}
			} catch (SQLException e) {
				System.out.println("cannot make statement in getAllComments!!!");
				return comments;
			}
			System.out.println("Comments loaded successfully");
			return comments;
		}
		
		
		public void addComment(Comment c){
			try {
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO comments ( text,"
						+ " date_and_time, title, username, number_of_likes, number_of_dislikes) VALUES (?,?,?,?,?,?);");
				
				Calendar cal = Calendar.getInstance();
				
				st.setString(1, c.getText());
				st.setDate(2, new java.sql.Date(cal.getTimeInMillis()));
				st.setInt(3, c.getIdNews());
				st.setString(4, c.getUsername());
				st.setInt(5, c.getLikes());
				st.setInt(6, c.getDislikes());
				
				st.executeUpdate();
				System.out.println("Comment added in db");
				// kak da vzema idComment na dobaveniq komentar!!!
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next())
	            {
	                int last_inserted_id = rs.getInt(1);
	                c.setIdComment(last_inserted_id);
	            }
				System.out.println("Comment added successfully");
			} catch (SQLException e) {
				System.out.println("did not save the comment");
				e.printStackTrace();
			}		
		}
		
		public void addChangeAfterLikeComment(int idComment){
			try {
				Statement st = DBManager.getInstance().getConnection().createStatement();			
				st.executeUpdate("UPDATE comments SET number_of_likes = number_of_likes + 1 WHERE idComments =" + idComment + ";");
				System.out.println("Like of comment added successfully");
			} catch (SQLException e) {
				System.out.println("did not save the like of comment ");
				e.printStackTrace();
			}		
		}
		
		public void addChangeAfterDislikeComment(int idComment){
			try {
				Statement st = DBManager.getInstance().getConnection().createStatement();			
				st.executeUpdate("UPDATE comments SET number_of_dislikes = number_of_dislikes + 1 WHERE idComments =" + idComment + ";");
				System.out.println("Dislike of comment added successfully");
			} catch (SQLException e) {
				System.out.println("did not save the dislike of comment ");
				e.printStackTrace();
			}		
		}

		public void addUserLike(int idComment, String username) {
			try {
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO like_comments_by_user ( Comments_idComments,"
						+ " users_that_like_comment) VALUES (?,?);");// ako e nuzhno mozhe da se naprawi query s 4 parametura// za sega ne e nuzhno
			
				
				st.setInt(1, idComment);
				st.setString(2, username);;
				
				
				st.executeUpdate();
				System.out.println("Comment added successfully");
			} catch (SQLException e) {
				System.out.println("did not save the comment");
				e.printStackTrace();
			}		
			
		}

		public void addUserDislike(int idComment, String username) {
			try {
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO dislike_comments_by_user ( Comments_idComments,"
						+ " users_that_dislike_comment) VALUES (?,?);");// su6toto ...
			
				
				st.setInt(1, idComment);
				st.setString(2, username);;
				
				
				st.executeUpdate();
				System.out.println("Comment added successfully");
			} catch (SQLException e) {
				System.out.println("did not save the comment");
				e.printStackTrace();
			}		
			
		}
}
