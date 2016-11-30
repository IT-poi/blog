package com.cuit.boke.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.User;
import com.cuit.boke.service.ArticleService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/test")
@ParentPackage("struts-default")
public class TestAction extends ActionSupport{
	
	private User user;
	
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
	
	
	@Action(value="login", results={
			@Result(name="success", location="/success.jsp"),
			@Result(name="error", location="/error.jsp")
	})
	public String success(){
		if(user==null){
			return "error";
		}else {
			if(user.getName().isEmpty()){
				return "error";
			}
		}
		return "success";
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


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
