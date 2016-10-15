
package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Comment;
import com.example.model.News;
import com.example.model.NewsManager;

@Controller
public class NewsController {
	
	private static final String IMAGES_LOCATION = "img/";

	@RequestMapping(value="/addnews", method=RequestMethod.GET)
	public String getAddNews(HttpSession s, Model model) {
		if(s.getAttribute("loggedAs") != null ){
			if(s.getAttribute("loggedAs").equals("admin")){		
				model.addAttribute("news", new News());
				model.addAttribute("categories" , NewsManager.getInstance().categories);
				return "addnews";
			}
		}
			s.invalidate();
		return "login";
	}
	
	@RequestMapping(value="/addnews" , method=RequestMethod.POST)
	public String addNews(@RequestParam("picturesurl") MultipartFile multiPartFile, Model model ,@ModelAttribute News n) {
		if(NewsManager.getInstance().validateNews(n.getTitle(), n.getText(), n.getCategory(), multiPartFile)){
			System.out.println("Validation Succsessful!");
			File fileOnDisk = new File("D:\\java\\workspace\\SpringDK\\MyProject\\src\\main\\webapp\\static\\img\\" + multiPartFile.getOriginalFilename());
			try {
				fileOnDisk.createNewFile();
				Files.copy(multiPartFile.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.out.println("ERROR UPLOADING FILE!!!");
				e.printStackTrace();
			}
			model.addAttribute("filename", multiPartFile.getOriginalFilename());
			NewsManager.getInstance().makeNews(n.getTitle(), n.getText(), n.getCategory(), IMAGES_LOCATION + multiPartFile.getOriginalFilename());
		}
		return "addnews";
	}
	
	@RequestMapping(value="/{idNews}", method=RequestMethod.GET)
	public String getPostId(@PathVariable("idNews") int id, Model model,HttpServletResponse resp) {
		News news = NewsManager.getInstance().getNewsByID(id);
		ArrayList<Comment> comments = new ArrayList<Comment>();
		for(Comment c :news.getAllCommentsForNews() ){
			comments.add(c);
		}
		model.addAttribute("news", news);
		model.addAttribute("comment", new Comment());
		model.addAttribute("comments", comments);
//		File pic = new File(news.getPicturesURL());
//		try {
//			Files.copy(pic.toPath(), resp.getOutputStream());
//		} catch (IOException e) {
//			System.out.println("Error with pic to resp!!");
//			e.printStackTrace();
//		}
		return "post";
	}
	
	@RequestMapping(value="/football", method=RequestMethod.GET)
	public String getFootball(Model model) {
		HashSet<News> footballNews = NewsManager.getInstance().searchNewsByCategory("Football");
		model.addAttribute("news", footballNews);
		model.addAttribute("category", "Football");
		return "category";
	}
	
	@RequestMapping(value="/basketball", method=RequestMethod.GET)
	public String getBasketball(Model model) {
		HashSet<News> basketballNews = NewsManager.getInstance().searchNewsByCategory("basketball");
		model.addAttribute("news", basketballNews);
		model.addAttribute("category", "Basketball");
		return "category";
	}
	
	@RequestMapping(value="/volleyball", method=RequestMethod.GET)
	public String getVolleyball(Model model) {
		HashSet<News> volleyballNews = NewsManager.getInstance().searchNewsByCategory("volleyball");
		model.addAttribute("news", volleyballNews);
		model.addAttribute("category", "Volleyball");
		return "category";
	}
	
	@RequestMapping(value="/formula1", method=RequestMethod.GET)
	public String getFormula1(Model model) {
		HashSet<News> formula1News = NewsManager.getInstance().searchNewsByCategory("formula1");
		model.addAttribute("news", formula1News);
		model.addAttribute("category", "Formula1");
		return "category";
	}


	
}
