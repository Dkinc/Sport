package com.example.model;

import java.util.HashSet;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.db.CommentDAO;
import com.example.model.db.NewsDAO;

public class NewsManager {

private int uniqeNewsId;
private HashSet<News> allNews;
public HashSet<String> categories;
	
	
	private static NewsManager instance;
	
	private NewsManager(){
		uniqeNewsId = 0;
		allNews = new HashSet<News>();
		for(News n : NewsDAO.getInstance().getAllNews()){
			if(n.getIdNews() > uniqeNewsId){
				uniqeNewsId = n.getIdNews();
			}
			loadAllCommentsForNews(n);
			allNews.add(n);
		}
		categories = new HashSet<String>();
		for(String s : NewsDAO.getInstance().getCategories()){
			categories.add(s);
		}
	}
	
	public synchronized HashSet<News> getMainNews(){
		HashSet<News> mainNews = new HashSet<News>();
		int count = 0;
		for(News n : allNews){
			if(count < 4){
			mainNews.add(n);
			count++;
			}
			else{
				break;
			}
		}
		return mainNews;
	}
	
	public synchronized static NewsManager getInstance(){
		if(instance == null){
			instance = new NewsManager();
		}
		return instance;
	}
	/*
	 * Accessible only for Admin (method addNews(...))
	 */
	public synchronized void makeNews(String title, String text, String category, String picturesURL, String videoURL){
		News n = new News(++uniqeNewsId,title, text, category, picturesURL, videoURL, 0);
		allNews.add(n);
		NewsDAO.getInstance().addNews(n);
	}
	
	public synchronized boolean validateNews(String title, String text, String category, MultipartFile mpf){
		 if(title != null && title != "" ){
			if(text !=null && text != ""  ){
				if(category != null && category != ""){
					if(mpf != null && !mpf.isEmpty()){
						return true;						
					}
				}
					
			}
		 }
		 return false;
		
	}
	
	 public synchronized void makeNews(String title, String text, String category, String picturesURL) {
		 News n = new News(++uniqeNewsId, title, text, category, picturesURL, 0);
			allNews.add(n);
			NewsDAO.getInstance().addNews(n);
			System.out.println("NEWS MADE");
	 }

	
	 public synchronized HashSet<News> searchNewsByTitle(String title){
		 HashSet<News> searchResult = new HashSet<News>();
			for (News news : allNews) {
				if(news.getTitle().contains(title) || title.contains(news.getTitle())){
					searchResult.add(news);
				}
			}
			return searchResult;
    }
	 /*
	  * ???????? ����� �� ����� ��� ���� ������ ������!
	  */
	 public synchronized News getNewsByTitle(String title){
			for (News news : allNews) {
				if(news.getTitle().equalsIgnoreCase(title)){
					return news;
				}
			}
			return null;
    }
	 
	 public synchronized News getNewsByID(int id){
			for (News news : allNews) {
				if(news.getIdNews() == id){
					return news;
				}
			}
			return null;
 }
	 
	 public synchronized HashSet<News> searchNewsByCategory(String category){
		 HashSet<News> searchResult = new HashSet<News>();
			for (News news : allNews) {
				if(news.getCategory().equalsIgnoreCase(category)){
					searchResult.add(news);
				}
			}
			return searchResult;
    }
	 
	 public void loadAllCommentsForNews(News news){
		 			for (Comment c : CommentDAO.getInstance().getAllComments()) {
		 				if(c.getIdNews() == news.getIdNews()){
		 					news.addComment(c);
		 				}
		 			}
		 		}
}
