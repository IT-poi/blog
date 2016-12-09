package com.cuit.boke.dto;

import java.io.Serializable;

public class Result<T> implements Serializable{
	private static final long serialVersionUID = 1L;

	//状态
	private String status;
	
	//数据
	private T data;
	
	//消息
	private String messages;
	
	
	public Result(String status, T data, String messages) {
		super();
		this.status = status;
		this.data = data;
		this.messages = messages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	
	
}
