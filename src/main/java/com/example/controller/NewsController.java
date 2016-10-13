
package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
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

import com.example.model.News;
import com.example.model.NewsManager;

@Controller
public class NewsController {
	
	private static final String IMAGES_LOCATION = "images\\news\\";

	@RequestMapping(value="/addnews", method=RequestMethod.GET)
	public String getAddNews(HttpSession s, Model model) {
		model.addAttribute("news", new News());
		Map refData = new HashMap();
		model.addAttribute("categories" , NewsManager.getInstance().categories);
		if(s.getAttribute("loggedAs").equals("admin")){
			return "addnews";
		}
			s.invalidate();
		return "login";
	}
	
	@RequestMapping(value="/addnews" , method=RequestMethod.POST)
	public String addNews(@RequestParam("picturesurl") MultipartFile multiPartFile, Model model ,@ModelAttribute News n) {
		if(NewsManager.getInstance().validateNews(n.getTitle(), n.getText(), n.getCategory(), multiPartFile)){
			System.out.println("Validation Succsessful!");
			File fileOnDisk = new File(IMAGES_LOCATION + multiPartFile.getOriginalFilename());
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
		model.addAttribute("news", news);
//		File pic = new File(news.getPicturesURL());
//		try {
//			Files.copy(pic.toPath(), resp.getOutputStream());
//		} catch (IOException e) {
//			System.out.println("Error with pic to resp!!");
//			e.printStackTrace();
//		}
		return "post";
	}

	
}
