package com.model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import com.model.News;

public class NewsDAO {


    private static NewsDAO instance;
	
	private NewsDAO(){}
	
	public synchronized static NewsDAO getInstance(){
		if(instance == null){
			instance = new NewsDAO();
		}
		return instance;
	}
	
	public HashSet<News> getAllNews(){
		HashSet<News> allNews = new HashSet<News>(); 
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery("SELECT N.title, N.number_of_reads, N.picture_address, N.video_address, N.text, C.category FROM"
					+ " news N INNER JOIN category_of_news C ON N.Category_of_news_idCategory_of_news = C.idCategory_of_news ORDER BY category Desc;");
			while(resultSet.next()){
				allNews.add(new News(	resultSet.getString("title"),
										resultSet.getString("text"),
										resultSet.getString("category"),
										resultSet.getString("picture_address"),
										resultSet.getString("video_address")
										));
			}
		} catch (SQLException e) {
			System.out.println(" Cannot make statement in getAllNews .");
			return allNews;
		}
		System.out.println("allNews loaded successfully");
		return allNews;
	}
	
	public void addNews(News n){
		try {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO news (title, text, Category_of_news_idCategory_of_news,"
					+ "picture_address, video_address) VALUES (?,?,?,?,?);");
			
			st.setString(1, n.getTitle());
			st.setString(2, n.getText());
			st.setInt(3, getCategoryId(n.getCategory()));
			st.setString(4, n.getPicturesURL());
			st.setString(5, n.getVideoURL());
		
			
			st.executeUpdate();
			System.out.println("News added successfully");
		} catch (SQLException e) {
			System.out.println("!!! did not save the news!!!");
			e.printStackTrace();
		}		
	}
	
	int getCategoryId(String category){
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery("SELECT  idCategory_of_news FROM category_of_news WHERE category = " + category + ";");
			return resultSet.getInt("idCategory_of_news");
			
		} catch (SQLException e) {
			System.out.println("problems with getCategoryId method !!!");
			e.printStackTrace();
		}
		return -1; 
	}
	
}
