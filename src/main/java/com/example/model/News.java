package com.example.model;

public class News {

	private String title;
	private String text;
	private String category;
//	private TreeSet<String> picturesURL;// picures for concrete news, #pictures = equal or more than 1 and less than 5 - complicated.More columns for pics in db .
	private String picturesURL;
	private String videoURL;
	private int numberOfReads;
	
	public News(String title, String text, String category, String picturesURL, String videoURL, int numberOfReads) {
		this.title = title;
		this.text = text;
		this.category = category;
		this.picturesURL = picturesURL;
		this.videoURL = videoURL;
	}

	public News(String title, String text, String category, String picturesURL, int numberOfReads) {
		this.title = title;
		this.text = text;
		this.category = category;
		this.picturesURL = picturesURL;
		this.numberOfReads = numberOfReads;
	}

	public News() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title != null && title != ""){
			this.title = title;
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if(text != null && text != ""){
			this.text = text;
		}
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		if(category != null && category != ""){
			this.category = category;
		}
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public int getNumberOfReads() {
		return numberOfReads;
	}

	public void setNumberOfReads(int numberOfReads) {
		if(numberOfReads>0){
			this.numberOfReads = numberOfReads;
		}
	}
	
	public String getPicturesURL() {
		return picturesURL;
	}

	public void setPicturesURL(String picturesURL) {
//		if(new Admin().isValidImageURL(picturesURL)){
//			this.picturesURL = picturesURL;
//		}
	}

}
