package com.example.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.HashSet;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.example.model.News;
import com.example.model.NewsManager;


@Controller
public class IndexController {
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getIndex() {
//		HashSet<News> mainNews = NewsManager.getInstance().getMainNews();
//		for(News n : mainNews){
//			File file = new File(n.getPicturesURL());
////			Files.copy(file.toPath(), resp.getOutputStream());
////			TODO 
//		}
		return "index";
	}
	

	
}
