package com.cuit.boke.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.User;
import com.cuit.boke.service.ArticleService;
import com.opensymphony.xwork2.ActionSupport;
@Controller("articleJsonAction")
public class ArticleJsonAction extends ActionSupport{
	
	@Autowired
	private ArticleService articleService;
	
	private Map articles = new HashMap();
	
	public Map getUsers() {
		return articles;
	}

	public void setUsers(Map articles) {
		this.articles = articles;
	}

	public String getUser(){
		PageBean<Article> pages;
		System.out.println("----JsonAction");
		pages = articleService.recentArticleByPage(1, 5);
		articles.put("articles", pages.getList());
		return SUCCESS;
	}
}
