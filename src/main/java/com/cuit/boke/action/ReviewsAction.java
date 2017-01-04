package com.cuit.boke.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cuit.boke.entity.Review;
import com.cuit.boke.service.ReviewService;
import com.opensymphony.xwork2.ActionSupport;

@Controller("reviewsAction")
@ParentPackage("json-default")
@Scope(value="prototype")
@Namespace("/reviews")
public class ReviewsAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private int reviewId;//评论id
	
	private String reviewCount;//评论内容
	
	private String name; //评论者name
	
	private String url; //评论者个人地址
	
	private String email; //评论者邮箱
	
	private int level; //评论的级别
	
	private String parentId; //父评论id
	
	private int articleId; //所属文章id
	
	private com.cuit.boke.dto.Result<String> result; //返回结果
	
	private com.cuit.boke.dto.Result<List<Review>> reviewResult;//返回文章下的所有结果
	
	
	@Autowired
	private ReviewService reviewService;
	

	
	/*------ 以下为所有前台可调用接口方法 ------*/
	
	@Action(value="sendReviews", results={
			@Result(type="json", params={"root","result"})
			})
	public String sendReviews(){
		Review review = new Review();
		review.setName(name);
		review.setContent(reviewCount);
		review.setLevel(level);
		review.setUrl(url);
		review.setEmail(email);
		try {
			reviewService.addReviews(articleId, parentId, review);
			result = new com.cuit.boke.dto.Result<String>("ok", "success", null);
		} catch (Exception e) {
			e.printStackTrace();
			result = new com.cuit.boke.dto.Result<String>("error", "failed", "评论失败！");
		}
		return SUCCESS;
	}
	/**
	 * 删除评论
	 * @return
	 */
	@Action(value="delete", results={
			@Result(type="json", params={"root","result"})
			})
	public String delete(){
		try {
			reviewService.deleteReview(reviewId);
			result = new com.cuit.boke.dto.Result<String>("ok", "success", null);
		} catch (Exception e) {
//			e.printStackTrace();
			result = new com.cuit.boke.dto.Result<String>("error", "failed", e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 拿到文章下的所有评论
	 * @return
	 */
	@Action(value="reviewList", results={
			@Result(type="json", params={"root","result"})
			})
	public String reviewList(){
		try {
			List<Review> reviews = reviewService.getReviews(articleId);
			reviewResult = new com.cuit.boke.dto.Result<List<Review>>("ok", reviews, null);
		} catch (Exception e) {
			reviewResult = new com.cuit.boke.dto.Result<List<Review>>("ok", null, e.getMessage());
		}
		return SUCCESS;
	}

	
	
	
	//setter and getter
	public com.cuit.boke.dto.Result<String> getResult() {
		return result;
	}

	public void setResult(com.cuit.boke.dto.Result<String> result) {
		this.result = result;
	}

	public String getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(String reviewCount) {
		this.reviewCount = reviewCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	} 
	
	public int getArticleId() {
		return articleId;
	}
	
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public com.cuit.boke.dto.Result<List<Review>> getReviewResult() {
		return reviewResult;
	}
	public void setReviewResult(com.cuit.boke.dto.Result<List<Review>> reviewResult) {
		this.reviewResult = reviewResult;
	}
	
}
