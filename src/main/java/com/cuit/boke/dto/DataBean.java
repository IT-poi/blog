package com.cuit.boke.dto;

public class DataBean {
	
	//总文章数
	private int articleCount;
	//总评论数
	private int reviewCount;
	//总浏览量
	private int viewCount;

	
	public DataBean(int articleCount, int reviewCount, int viewCount) {
		super();
		this.articleCount = articleCount;
		this.reviewCount = reviewCount;
		this.viewCount = viewCount;
	}
	
	public DataBean() {
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	

}
