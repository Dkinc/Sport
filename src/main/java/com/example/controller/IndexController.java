package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.News;
import com.example.model.NewsManager;


@Controller
@SessionAttributes("filename")
@MultipartConfig
public class IndexController {
	
	private static final String FILE_LOCATION = "images\\news\\";
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getIndex(HttpServletResponse resp, Model m) {
		HashSet<News> mainNews = NewsManager.getInstance().getMainNews();
		m.addAttribute("news", mainNews);
		HashSet<News> footballNews = NewsManager.getInstance().searchNewsByCategory("Football");
		m.addAttribute("football",footballNews);
		HashSet<News> basketballNews = NewsManager.getInstance().searchNewsByCategory("Basketball");
		m.addAttribute("basketball",basketballNews);
		HashSet<News> volleyballNews = NewsManager.getInstance().searchNewsByCategory("Volleyball");
		m.addAttribute("volleyball",volleyballNews);
		HashSet<News> f1News = NewsManager.getInstance().searchNewsByCategory("Formula1");
		m.addAttribute("formula1",f1News);
		
//		for(News n : mainNews){
//			File file = new File(n.getPicturesURL());
//			try {
//				Files.copy(file.toPath(), resp.getOutputStream());
//			} catch (IOException e) {
//				System.out.println("Failed streeming file");
//				e.printStackTrace();
//			}
//		}
		return "index";
	}
	


	
}
