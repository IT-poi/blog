package com.cuit.boke.dto;

import java.util.List;

import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.Review;


public class ArticleBean {
	private Article article;
	
	private List<Review> reviews;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public ArticleBean(Article article, List<Review> reviews) {
		this.article = article;
		this.reviews = reviews;
	}
}
