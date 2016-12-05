package com.cuit.boke.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.service.ArticleService;
import com.opensymphony.xwork2.ActionSupport;
@Controller("articleJsonAction")
@SuppressWarnings("unchecked")
public class ArticleJsonAction extends ActionSupport{
	
	private static final long serialVersionUID = 644078581132832173L;

	@Autowired
	private ArticleService articleService;
	
	private Map recentPages = new HashMap();
	
	public Map getRecentPages() {
		return recentPages;
	}

	public void setRecentPages(Map recentPages) {
		this.recentPages = recentPages;
	}

	public String getPages(){
		PageBean<Article> pages;
		System.out.println("----JsonAction");
		pages = articleService.recentArticleByPage(1, 5, PageBean.TIME);
		recentPages.put("recentPages", pages);
		return SUCCESS;
	}
}
