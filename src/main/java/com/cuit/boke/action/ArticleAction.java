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
		@Result(name="success",type="json",params={"root","data"})
		)
public class ArticleAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ArticleService articleService;
	
	private Map<String,com.cuit.boke.dto.Result<ArticleBean>> data = new HashMap<String,com.cuit.boke.dto.Result<ArticleBean>>();
	
	private int articleId;
	
	@Action(value="getArticle")
	@Override
	public String execute() throws Exception {
		ArticleBean articleBean = articleService.queryArticleById(articleId);
		com.cuit.boke.dto.Result<ArticleBean> result = new com.cuit.boke.dto.Result<ArticleBean>("OK", articleBean, null);
		data.put("result", result);
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

}
