package com.example.model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashSet;

import com.example.model.Comment;
import com.example.model.CommentManager;
import com.example.model.UsersManager;

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

				ResultSet resultSet = st.executeQuery("SELECT C.idComments, C.text, C.date_and_time, C.number_of_likes,"
						+ " C.number_of_dislikes, U.username, N.idNews"
						+ " FROM comments C INNER JOIN users U ON C.Users_idUsers = U.idUsers"
						+ " INNER JOIN news N ON C.News_idNews = N.idNews"
						+ " ORDER BY idNews Desc;");

				while(resultSet.next()){
					
					Comment c = new Comment(	resultSet.getInt("idComments"),
												resultSet.getString("text"),
												resultSet.getTimestamp("date_and_time").toLocalDateTime(),
												resultSet.getInt("idNews"),
												resultSet.getString("username")
											);
					c.setLikes(resultSet.getInt("number_of_likes"));
					c.setDislikes(resultSet.getInt("number_of_dislikes"));
					comments.add(c);
					System.out.println("Comments loaded successfully");
				}
			} catch (SQLException e) {
				System.out.println("PROBLEMS in getAllComments!!!");

				e.printStackTrace();
			}
			
			return comments;
		}
		
		
		public void addComment(Comment c){
			try {
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO comments ( text,"
						+ " date_and_time, News_idNews, Users_idUsers, number_of_likes, number_of_dislikes) VALUES (?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS);
				
				Calendar cal = Calendar.getInstance();
				
				st.setString(1, c.getText());
				st.setDate(2, new java.sql.Date(cal.getTimeInMillis()));
				st.setInt(3, c.getIdNews());
				st.setInt(4, UsersManager.getInstance().getUser(c.getUsername()).getIdUser());
				st.setInt(5, c.getLikes());
				st.setInt(6, c.getDislikes());
				
				st.executeUpdate();
				System.out.println("Comment added in db");
				// kak da vzema idComment na dobaveniq komentar!!!
//				ResultSet rs = st.getGeneratedKeys();
//				if(rs.next())
//	            {
//	                int last_inserted_id = rs.getInt(1);
//	                c.setIdComment(last_inserted_id);
//	            }
//				System.out.println("Comment added successfully");
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
						+ " users_that_like_comment, Comments_Users_idUsers, Comments_News_idNews) VALUES (?,?,?,?);");
			
				
				st.setInt(1, idComment);
				st.setString(2, username);
				st.setInt(3, UsersManager.getInstance().getUser(CommentManager.getInstance().getCommentByID(idComment).getUsername()).getIdUser());
				st.setInt(4, CommentManager.getInstance().getCommentByID(idComment).getIdNews());
				
				st.executeUpdate();
				System.out.println("UserLike added successfully in like_comments_by_user");
			} catch (SQLException e) {
				System.out.println("did not save UserLike in the like_comments_by_user");
				e.printStackTrace();
			}		
			
		}

		public void addUserDislike(int idComment, String username) {
			try {
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO dislike_comments_by_user ( Comments_idComments,"
						+ " users_that_dislike_comment, Comments_Users_idUsers, Comments_News_idNews) VALUES (?,?,?,?);");
			
				
				st.setInt(1, idComment);
				st.setString(2, username);
				st.setInt(3, UsersManager.getInstance().getUser(CommentManager.getInstance().getCommentByID(idComment).getUsername()).getIdUser());
				st.setInt(4, CommentManager.getInstance().getCommentByID(idComment).getIdNews());
				
				st.executeUpdate();
				System.out.println("UserDislike added successfully in dislike_comments_by_user");
			} catch (SQLException e) {
				System.out.println("did not save UserDislike in the dislike_comments_by_user");
				e.printStackTrace();
			}		
			
		}
}
