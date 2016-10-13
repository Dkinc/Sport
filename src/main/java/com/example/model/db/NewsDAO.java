package com.example.model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import com.example.model.News;

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
				allNews.add(new News(	resultSet.getInt("idNews"),
						                resultSet.getString("title"),
										resultSet.getString("text"),
										resultSet.getString("category"),
										resultSet.getString("picture_address"),
										resultSet.getString("video_address"),
										resultSet.getInt("number_of_reads")
										));
			}
		} catch (SQLException e) {
			System.out.println("Cannot make statement in getAllNews .");
			return allNews;
		}
		System.out.println("allNews loaded successfully");
		return allNews;
	}
	
	public void addNews(News n){
		try {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO news (title, text, Category_of_news_idCategory_of_news,"
					+ "picture_address, video_address, number_of_reads) VALUES (?,?,?,?,?,?);");
			
			st.setString(1, n.getTitle());
			st.setString(2, n.getText());
			st.setInt(3, getIdByCategory().get(n.getCategory()));
			st.setString(4, n.getPicturesURL());
			st.setString(5, n.getVideoURL());
			st.setInt(6, n.getNumberOfReads());
		
			
			st.executeUpdate();
			System.out.println("News added successfully");
		} catch (SQLException e) {
			System.out.println("!!! did not save the news!!!");
			e.printStackTrace();
		}		
	}
	
	public synchronized HashSet<String> getCategories(){
		HashSet<String> categories = new HashSet<>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery("SELECT category FROM category_of_news");
			while(resultSet.next()){
				categories.add(resultSet.getString("category"));
			}
			return categories;
			
		} catch (SQLException e) {
			System.out.println("problems with getCategories method !!!");
			e.printStackTrace();
		}
		return categories;
	}
	
	public synchronized HashMap<String,Integer> getIdByCategory(){
		HashMap<String,Integer> categories = new HashMap<>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery("SELECT idCategory_of_news, category FROM category_of_news");
			while(resultSet.next()){
				categories.put( resultSet.getString("category"), resultSet.getInt("idCategory_of_news"));
			}
			return categories;
			
		} catch (SQLException e) {
			System.out.println("problems with getCategories method !!!");
			e.printStackTrace();
		}
		return categories;
	}
	
}
