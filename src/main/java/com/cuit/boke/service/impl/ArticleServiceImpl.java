package com.cuit.boke.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuit.boke.dao.ArticleDao;
import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.service.ArticleService;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;
	
	public PageBean<Article> recentArticleByPage(int currPage, int pageSize) {
		PageBean<Article> pageBean = new PageBean<Article>();
		//封装当前页数
		pageBean.setCurrPage(currPage);
		//封装每页显示的记录数
		pageBean.setPageSize(pageSize);
		//封装总记录条数
		int totalCount = articleDao.queryTotalCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
//		Double pageCount = Math.ceil(totalCount / pageSize);
		int pageCount = (totalCount + pageSize -1) / pageSize;
		pageBean.setTotalPage(pageCount);
		//封装每页显示的数据
		int begin = (currPage - 1)*pageSize;
		System.out.println("---------Service");
		List<Article> list = articleDao.queryByPage(begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	public int saveArticle(Article article) {
		return articleDao.insert(article);
	}

}
