package com.cuit.boke.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="manager")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Manager {
	// 管理员id
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// 管理员登录用户名
	@Column(nullable=false)
	private String number;
	
	// 管理员登录的密码
	@Column(nullable=false)
	private String password;
	
	// 个人说明
	@Column
	private String elucidation;
	
	/**
	 * 管理员相关个人说明信息,可以自行增添。
	 */
	
	// 地址
	@Column
	private String address;
	
	// 职业
	@Column
	private String perfessional;
	
	// 个性签名
	@Column
	private String signature;
	
	@OneToMany(mappedBy="manager",targetEntity=Article.class)
	private Set<Article> articles = new HashSet<Article>();	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getElucidation() {
		return elucidation;
	}

	public void setElucidation(String elucidation) {
		this.elucidation = elucidation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPerfessional() {
		return perfessional;
	}

	public void setPerfessional(String perfessional) {
		this.perfessional = perfessional;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", number=" + number + ", password=" + password + ", elucidation=" + elucidation
				+ ", address=" + address + ", perfessional=" + perfessional + ", signature=" + signature + "]";
	}
	
	
	
}
