package com.cuit.boke.entity;

import java.sql.Date;

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

@Entity
@Table(name="article")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Article {
	// 文章主键
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// 文章标题
	@Column(nullable=false,length=255)
	private String title; 
	
	// 文章内容
	@Column(nullable=false,length=10000)
	private String content; 

	// 文章概要
	@Column(nullable=false,length=100)
	private String brief; 
	
	// 图片URL,默认地址为"/"
	@Column(nullable=false,columnDefinition="varchar(255) default '/picture/'")
	private String imgURL; 

	// 文章上传时间(文章第一次上传的时间)
	@Column(nullable=false,name="create_time")
	private Date createTime;

	// 文章浏览量
	@Column(name="page_view",columnDefinition="int default 0",nullable=false)
	private int pageView;
	
	// 文章的标签，以分隔符(,)分开
	@Column
	private String label; 
	
	// 博客本人
	@ManyToOne(targetEntity=Manager.class)
	@JoinColumn(name="manager_id",nullable=false)
	private int managerId;

	// 文章评论数
	@Column(name="comment_num",columnDefinition="int default 0",nullable=false)
	private int commentNum;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getPageView() {
		return pageView;
	}

	public void setPageView(int pageView) {
		this.pageView = pageView;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", brief=" + brief + ", imgURL="
				+ imgURL + ", createTime=" + createTime + ", pageView=" + pageView + ", label=" + label + ", managerId="
				+ managerId + ", commentNum=" + commentNum + "]";
	}
	
	
}
