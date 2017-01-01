package com.cuit.boke.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuit.boke.dao.ArticleDao;
import com.cuit.boke.dao.ReviewDao;
import com.cuit.boke.dao.impl.ManagerDaoImpl;
import com.cuit.boke.dto.ArticleBean;
import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.Manager;
import com.cuit.boke.entity.Review;
import com.cuit.boke.exception.DeleteFailException;
import com.cuit.boke.exception.SaveFailException;
import com.cuit.boke.exception.UnknowException;
import com.cuit.boke.exception.UpdateFailException;
import com.cuit.boke.service.ArticleService;
@Transactional
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ManagerDaoImpl managerDaoImpl;
	@Autowired
	private ReviewDao reviewDao;
	
	public PageBean<Article> recentArticleByPage(PageBean<Article> pageBean) throws UnknowException {
		if (pageBean == null) {
			throw new UnknowException("pageBean为空");
		}
		//封装当前页数
//		pageBean.setCurrPage(pageBean.getCurrPage());
		//封装每页显示的记录数
//		pageBean.setPageSize(pageBean.getPageSize());
		//封装总记录条数
		int totalCount = articleDao.queryTotalCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
//		Double pageCount = Math.ceil(totalCount / pageSize);
		int pageCount = (totalCount + pageBean.getPageSize() -1) / pageBean.getPageSize();
		pageBean.setTotalPage(pageCount);
		if (pageBean.getCurrPage() > pageCount) {
			throw new UnknowException("请求页面参数错误");
		}
		//封装每页显示的数据
		int begin = (pageBean.getCurrPage() - 1)*pageBean.getPageSize();
		List<Article> list = articleDao.queryByPage(Article.class, begin, pageBean.getPageSize(), pageBean.getOrderBy(), pageBean.getOrder());
		pageBean.setList(list);
		return pageBean;
	}

	public int saveArticle(Article article) throws SaveFailException{
		int articleId = -1;
		try {
			Manager manager = managerDaoImpl.queryById(Manager.class, 1);
			article.setManager(manager);
			articleId = articleDao.insert(article);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SaveFailException("保存失败!");
		}
		return articleId;
	}

	public void updateArticle(Article article) throws UpdateFailException {
		try {
			Article article2 = articleDao.queryById(Article.class, article.getId());
			article2.setTitle(article.getTitle());
			article2.setBrief(article.getBrief());
			article2.setContent(article.getContent());
			article2.setImgURL(article.getImgURL());
			article2.setLabel(article.getLabel());
			article2.setStick(article.isStick());
			articleDao.update(article2);
		} catch (Exception e) {
			throw new UpdateFailException("修改失败！");
		}
	}

	public void deleteArticle(Article article) throws DeleteFailException {
		try {
			articleDao.deleteById(Article.class, article.getId());
		} catch (Exception e) {
			throw new DeleteFailException("删除失败");
		}
	}
	
	public ArticleBean queryArticleById(String blogger, int articleId) {
		Article article = articleDao.queryById(Article.class, articleId);
		if (blogger != null) {
			if(blogger.equals("true")){
				article.setPageView(article.getPageView()+1);
				articleDao.update(article);
			}
		}
		List<Review> reviews = reviewDao.queryByArticleId(articleId);
		ArticleBean articleBean = new ArticleBean(article, reviews);
		return articleBean;
	}
	public PageBean<Article> articlePageByLable(String label, PageBean<Article> pageBean)
			throws UnknowException {
		if (pageBean == null) {
			throw new UnknowException("pageBean为空");
		}
		//封装当前页数
//		pageBean.setCurrPage(pageBean.getCurrPage());
		//封装每页显示的记录数
//		pageBean.setPageSize(pageBean.getPageSize());
		//封装总记录条数
		int totalCount = articleDao.queryTotalCountByLabel(label);
		pageBean.setTotalCount(totalCount);
		//封装总页数
//		Double pageCount = Math.ceil(totalCount / pageSize);
		int pageCount = (totalCount + pageBean.getPageSize() -1) / pageBean.getPageSize();
		pageBean.setTotalPage(pageCount);
		if (pageBean.getCurrPage() > pageCount) {
			throw new UnknowException("请求页面参数错误");
		}
		//封装每页显示的数据
		int begin = (pageBean.getCurrPage() - 1)*pageBean.getPageSize();
		List<Article> list = articleDao.queryByLabelPage(label, begin, pageBean.getPageSize());
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Article> articlePageByKeywords(String keywords,
			PageBean<Article> pageBean) throws UnknowException {
		if (pageBean == null) {
			throw new UnknowException("pageBean为空");
		}
		//封装当前页数
//		pageBean.setCurrPage(pageBean.getCurrPage());
		//封装每页显示的记录数
//		pageBean.setPageSize(pageBean.getPageSize());
		//封装总记录条数
		int totalCount = articleDao.queryTotalCountByKeywords(keywords);
		pageBean.setTotalCount(totalCount);
		//封装总页数
//		Double pageCount = Math.ceil(totalCount / pageSize);
		int pageCount = (totalCount + pageBean.getPageSize() -1) / pageBean.getPageSize();
		pageBean.setTotalPage(pageCount);
		if (pageBean.getCurrPage() > pageCount || pageBean.getCurrPage()<=0) {
			throw new UnknowException("请求页面参数错误");
		}
		//封装每页显示的数据
		int begin = (pageBean.getCurrPage() - 1)*pageBean.getPageSize();
		List<Article> list = articleDao.queryByKeywordsPage(keywords, begin, pageBean.getPageSize());
		pageBean.setList(list);
		return pageBean;
	}

	public void deleteArticleById(int articleId) throws DeleteFailException {
		try {
			articleDao.deleteById(Article.class, articleId);
		} catch (Exception e) {
			throw new DeleteFailException("删除失败");
		}
	}

	public int articleCount() {
		return articleDao.queryTotalCount();
	}

	public int allArticleViewCount() {
		List<Article> articles = articleDao.queryAll(Article.class);
		int articleCount = 0;
		for (Article article : articles) {
			articleCount += article.getPageView();
		}
		return articleCount;
	}
}
