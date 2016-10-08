package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.News;
import com.model.NewsManager;

@Controller
@RequestMapping(value = "/SearchAndGetNews")
public class SearchAndGetNewsController {

    @RequestMapping(value = "/searchNewsByTitle", method = RequestMethod.GET)
	public String searchNewsByTitle(Model model, HttpServletRequest req){
		
		String title = req.getParameter("title");
		if(NewsManager.getInstance().searchNewsByTitle(title).isEmpty()){
			title = null;
			model.addAttribute("title", null);
		}
		model.addAttribute("title", title);
		return "search"; // search.jsp
	}
    
    @RequestMapping(value = "/searchNewsByCategory", method = RequestMethod.GET)
   	public String searchNewsByCategory(Model model, HttpServletRequest req){
   		
   		String category = req.getParameter("category");
   		
   		if(NewsManager.getInstance().searchNewsByTitle(category).isEmpty()){
   			category = null;
   			model.addAttribute("category", null);
   		}
   		
   		model.addAttribute("category", category);
   		return "search";
   	}
    
    @RequestMapping(value = "/getNews", method = RequestMethod.GET)
   	public String getNews(Model model, HttpServletRequest req){
   		
    	News news = NewsManager.getInstance().getNewsByTitle(req.getParameter("title"));
    	model.addAttribute("title", news.getTitle());
    	model.addAttribute("text", news.getText());
    	model.addAttribute("category", news.getCategory());
		model.addAttribute("picturesURL", news.getPicturesURL());
		model.addAttribute("videoURL", news.getVideoURL());
		
   		return "showNews";
   	}
}
