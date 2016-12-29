package com.cuit.boke.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
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

	private int currPage; //当前页数
	
	private int pageSize; //每页显示记录数
	
	private int totalCount; //总记录数
	
	private int totalPage; //总页数
	
	private String orderBy; //排序方式，可以通过最新和最热排序
	
	private String order; //排序方式，升序或者降序
	

	@Autowired
	private ArticleService articleService;
	
	private com.cuit.boke.dto.Result<PageBean<Article>> result;
	
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

	@Action(value="pagelist", results={
			@Result(type="json", params={"root","result"})
			})
	public String getPages(){
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

	public PageBean<Article> getModel() {
//		if(pageBean == null){
//			pageBean = new PageBean<Article>();
//		}
		return pageBean;
	}
}
