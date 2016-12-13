package com.cuit.boke.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity()
@Table(name = "review")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Review {
	// 评论id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 该评论的级别
	@Column(nullable = false)
	private int level;

	// 该评论的内容
	@Column(nullable = false)
	private String content;

	// 该评论的用户名
	@Column(nullable = false)
	private String name;

	// 该评论作者的头像URL地址
	@Column(name = "portrait_url", nullable = false)
	private String portraitURL;

	// 该评论生成时间
	@Column(name = "create_time", nullable = false)
	private Date cteateTime;

	// 该评论获得的赞的数目
	@Column(name = "praise_num", columnDefinition = "int default 0", nullable = false)
	private int praiseNum;

	// 该评论获得的踩的数目
	@Column(name = "stamp_num", columnDefinition = "int default 0", nullable = false)
	private int stampNum;

	// 该评论的父类
	@ManyToOne(targetEntity = Review.class)
	@JoinColumn(name = "parent_id")
	private Review parentReview;


	// 该评论对应的文章id
	@ManyToOne(targetEntity = Article.class)
	@JoinColumn(name = "article_id", nullable = false,referencedColumnName="id")
	private Article article;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPortraitURL() {
		return portraitURL;
	}

	public void setPortraitURL(String portraitURL) {
		if (portraitURL.isEmpty()) {
			portraitURL = "/pictrue/1.png";
		}
		this.portraitURL = portraitURL;
	}

	public Date getCteateTime() {
		return cteateTime;
	}

	public void setCteateTime(Date cteateTime) {
		this.cteateTime = cteateTime;
	}

	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	public int getStampNum() {
		return stampNum;
	}

	public void setStampNum(int stampNum) {
		this.stampNum = stampNum;
	}

	public Review getParentReview() {
		return parentReview;
	}

	public void setParentReview(Review parentReview) {
		this.parentReview = parentReview;
	}


	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", level=" + level + ", content=" + content + ", name=" + name + ", portraitURL="
				+ portraitURL + ", cteateTime=" + cteateTime + ", praiseNum=" + praiseNum + ", stampNum=" + stampNum
				+ ", parentReview=" + parentReview + ", article=" + article + "]";
	}
	
	
}
