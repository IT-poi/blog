package com.cuit.boke.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cuit.boke.entity.Article;
import com.cuit.boke.service.ArticleService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/test")
@ParentPackage("struts-default")
public class TestAction extends ActionSupport{
	
	
	private static final long serialVersionUID = 5857514152555579576L;
	
	private Article article;
	
	public Article getArticle() {
		return article;
	}
	@Autowired
	private ArticleService articleService;

	@Action(value="index", results={
			@Result(name="index",location="/index.jsp")
	})
	public String index(){
		return "index";
	}
	
	@Action(value="commit", results={
			@Result(name="success", location="/success.jsp"),
			@Result(name="error", location="/error.jsp")
	})
	public String getArticlePage(){
		Integer articleId = 0;
//		try {
			System.out.println(article.getBrief());
			articleId = articleService.saveArticle(article);
			System.out.println(articleId);
//		} catch (Exception e) {
//			return "error";
//		}
		return "success";
	}
	
	public void setArticle(Article article) {
		this.article = article;
	}

}
