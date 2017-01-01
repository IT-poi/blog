package com.cuit.boke.action;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.Manager;
import com.cuit.boke.service.ArticleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
	
@Controller("articleJsonAction")
@ParentPackage("json-default")
@Scope(value="prototype")
@Namespace("/article")
@SuppressWarnings("unchecked")
public class ArticleJsonAction extends ActionSupport implements ModelDriven<PageBean<Article>>{
	
	private static final long serialVersionUID = 644078581132832173L;
	
	
	private PageBean<Article> pageBean;
	
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getKeywords() {
		return keywords;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}

	private int currPage; //当前页数
	
	private int pageSize; //每页显示记录数
	
	private int totalCount; //总记录数
	
	private int totalPage; //总页数
	
	private int articleId; //文章id
	
	private String orderBy; //排序方式，可以通过最新和最热排序
	
	private String order; //排序方式，升序或者降序
	
	private String keywords; //关键字
	
	private String label; //文章标签
	
	private String title; //文章标题
	
	private String brief; // 文章概要

	private String content; // 文章内容
	
	private String imgURL; // 图片URL,默认地址为"/"

	private boolean isStick; //是否置顶，默认为否

	private int managerId; //博主id

	
	@Autowired
	private ArticleService articleService;
	
	private com.cuit.boke.dto.Result<PageBean<Article>> result;
	
	private com.cuit.boke.dto.Result<String> stringResult;
	
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
	
	public void setStringResult(com.cuit.boke.dto.Result<String> stringResult) {
		this.stringResult = stringResult;
	}
	
	public com.cuit.boke.dto.Result<String> getStringResult() {
		return stringResult;
	}

	/**
	 * 查询文章列表
	 * @return
	 */
	@Action(value="pagelist", results={
			@Result(type="json", params={"root","result"})
			})
	public String getPages(){
		PageBean<Article> pages;
		PageBean<Article> pageBean = new PageBean<Article>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		pageBean.setOrderBy(orderBy);
		System.out.println(order);
		pageBean.setOrder(order);
		pageBean.setTotalPage(totalPage);
		pageBean.setTotalCount(totalCount);
		System.out.println(currPage);
		System.out.println("----JsonAction");
		System.out.println(pageBean);
		try {
			pages = articleService.recentArticleByPage(pageBean);
			result = new com.cuit.boke.dto.Result<PageBean<Article>>("ok", pages, null);
		} catch (Exception e) {
//			e.printStackTrace();
			result = new com.cuit.boke.dto.Result<PageBean<Article>>("error", null, e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 查询文章列表通过标签
	 * @return
	 */
	@Action(value="pagelistLabel", results={
			@Result(type="json", params={"root","result"})
			})
	public String getPagesByLabel(){
		PageBean<Article> pages;
		PageBean<Article> pageBean = new PageBean<Article>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setTotalCount(totalCount);
		System.out.println(currPage);
		System.out.println("----JsonAction");
		System.out.println(pageBean);
		try {
			pages = articleService.articlePageByLable(label, pageBean);
			result = new com.cuit.boke.dto.Result<PageBean<Article>>("ok", pages, null);
		} catch (Exception e) {
//			e.printStackTrace();
			result = new com.cuit.boke.dto.Result<PageBean<Article>>("error", null, e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 通过关键字查询文章列表
	 * 
	 */
	@Action(value="keywordsPagelist", results={
			@Result(type="json", params={"root","result"})
			})
	public String getPagesByKeywords(){
		PageBean<Article> pages;
		PageBean<Article> pageBean = new PageBean<Article>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		pageBean.setOrderBy(orderBy);
		pageBean.setOrder(order);
		pageBean.setTotalPage(totalPage);
		pageBean.setTotalCount(totalCount);
		System.out.println(currPage);
		System.out.println("----JsonAction");
		System.out.println(pageBean);
		try {
			pages = articleService.articlePageByKeywords(keywords, pageBean);
			result = new com.cuit.boke.dto.Result<PageBean<Article>>("ok", pages, null);
		} catch (Exception e) {
//			e.printStackTrace();
			result = new com.cuit.boke.dto.Result<PageBean<Article>>("error", null, e.getMessage());
		}
		
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
		try {
			pages = articleService.recentArticleByPage(pageBean);
			result = new com.cuit.boke.dto.Result<PageBean<Article>>("OK", pages, "OK");
		} catch (Exception e) {
//			e.printStackTrace();
			result = new com.cuit.boke.dto.Result<PageBean<Article>>("error", null, e.getMessage());
		}
		return SUCCESS;
	}
	//删除文章
	@Action(value="delete", results={
			@Result(type="json", params={"root","stringResult"})
			})
	public String deleteArticle(){
		articleId = 1;
		try {
			stringResult = new com.cuit.boke.dto.Result<String>("ok", "success", null);
			articleService.deleteArticleById(articleId);
		} catch (Exception e) {
			stringResult = new com.cuit.boke.dto.Result<String>("error", "failed", "删除文章失败！");
		}
		return SUCCESS;
	}
	//修改文章
	@Action(value="delete", results={
			@Result(type="json", params={"root","stringResult"})
			})
	public String updateArticle(){
		Article article = new Article();
		article.setId(articleId);
		article.setCreateTime(new Date());
		article.setTitle(title);
		article.setBrief(brief);
		article.setContent(content);
		article.setImgURL(imgURL);
		article.setLabel(label);
		article.setStick(isStick);
		try {
			articleService.updateArticle(article);
			stringResult = new com.cuit.boke.dto.Result<String>("ok", "success", null);
		} catch (Exception e) {
			stringResult = new com.cuit.boke.dto.Result<String>("error", "failed", "修改文章失败！");
		}
		return SUCCESS;
	}
	
	//修改文章
		@Action(value="add", results={
				@Result(type="json", params={"root","stringResult"})
				})
		public String addArticle(){
			Article article = new Article();
			article.setTitle(title);
			article.setBrief(brief);
			article.setContent(content);
			article.setImgURL(imgURL);
			article.setLabel(label);
			article.setStick(isStick);
			try {
				articleService.saveArticle(article);
				stringResult = new com.cuit.boke.dto.Result<String>("ok", "success", null);
			} catch (Exception e) {
//				e.printStackTrace();
				stringResult = new com.cuit.boke.dto.Result<String>("error", "failed", "修改文章失败！");
			}
			return SUCCESS;
		}
	public PageBean<Article> getModel() {
//		if(pageBean == null){
//			pageBean = new PageBean<Article>();
//		}
		return pageBean;
	}
	
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	public int getArticleId() {
		return articleId;
	}
}
