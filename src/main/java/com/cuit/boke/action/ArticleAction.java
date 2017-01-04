package com.cuit.boke.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cuit.boke.dto.ArticleBean;
import com.cuit.boke.service.ArticleService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace(value="/ArticleAction")
@Scope(value="prototype")
@ParentPackage(value="json-default")
@Results(
		@Result(name="success",type="json",params={"root","result"})
		)
public class ArticleAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private com.cuit.boke.dto.Result<ArticleBean> result;
	
	@Autowired
	private ArticleService articleService;
	
	private Map<String,com.cuit.boke.dto.Result<ArticleBean>> data = new HashMap<String,com.cuit.boke.dto.Result<ArticleBean>>();
	
	private int articleId;
	
	private String blogger;
	
	@Action(value="getArticle")
	@Override
	public String execute() throws Exception {
		try {
			System.out.println("-----------------------------------");
			System.out.println(blogger);
			ArticleBean articleBean = articleService.queryArticleById(blogger, articleId);
//			blogger = null;
			result = new com.cuit.boke.dto.Result<ArticleBean>("ok", articleBean, null);
		} catch (Exception e) {
			e.printStackTrace();
			result = new com.cuit.boke.dto.Result<ArticleBean>("error", null, "系统错误");
		}
		return SUCCESS;
	}

	public Map<String, com.cuit.boke.dto.Result<ArticleBean>> getData() {
		return data;
	}

	public void setData(Map<String, com.cuit.boke.dto.Result<ArticleBean>> data) {
		this.data = data;
	}

	@JSON(serialize=false)
	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	public void setResult(com.cuit.boke.dto.Result<ArticleBean> result) {
		this.result = result;
	}
	
	public com.cuit.boke.dto.Result<ArticleBean> getResult() {
		return result;
	}

	public String getBlogger() {
		return blogger;
	}

	public void setBlogger(String blogger) {
		this.blogger = blogger;
	}

}
