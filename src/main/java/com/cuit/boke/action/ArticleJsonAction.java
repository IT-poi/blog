package com.cuit.boke.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.service.ArticleService;
import com.opensymphony.xwork2.ActionSupport;
	
@Controller("articleJsonAction")
@ParentPackage("json-default")
@Namespace("/article")
@SuppressWarnings("unchecked")
public class ArticleJsonAction extends ActionSupport{
	
	private static final long serialVersionUID = 644078581132832173L;

	@Autowired
	private ArticleService articleService;
	
	private com.cuit.boke.dto.Result<PageBean<Article>> result;
	
	private PageBean<Article> pageBean;
	
	public PageBean<Article> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Article> pageBean) {
		this.pageBean = pageBean;
	}

	public com.cuit.boke.dto.Result<PageBean<Article>> getResult() {
		return result;
	}

	public void setResult(com.cuit.boke.dto.Result<PageBean<Article>> result) {
		this.result = result;
	}

	@Action(value="list2", results={
			@Result(type="json", params={"root","result"})
			})
	public String getPages(){
		PageBean<Article> pages;
//		PageBean<Article> pageBean = new PageBean<Article>();
//		pageBean.setCurrPage(1);
//		pageBean.setPageSize(5);
//		pageBean.setOrderBy(PageBean.TIME);
//		pageBean.setOrder(PageBean.ASC);
		System.out.println("----JsonAction");
		System.out.println(pageBean);
		pages = articleService.recentArticleByPage(pageBean);
		result = new com.cuit.boke.dto.Result<PageBean<Article>>("OK", pages, "OK");
		return SUCCESS;
	}
	
	@Action(value="list", results={
			@Result(type="json", params={"root","result"})
			})
	public String getPages2(){
		PageBean<Article> pages;
		PageBean<Article> pageBean = new PageBean<Article>();
		pageBean.setCurrPage(1);
		pageBean.setPageSize(5);
		pageBean.setOrderBy(PageBean.TIME);
		pageBean.setOrder(PageBean.ASC);
		System.out.println("----JsonAction");
		pages = articleService.recentArticleByPage(pageBean);
		result = new com.cuit.boke.dto.Result<PageBean<Article>>("OK", pages, "OK");
		return SUCCESS;
	}
}
