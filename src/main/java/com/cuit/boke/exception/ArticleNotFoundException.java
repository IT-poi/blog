package com.cuit.boke.exception;

/**
 * 文章未找到Exception
 * @author inori
 *
 */
public class ArticleNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 125710454646430297L;

	public ArticleNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ArticleNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
}
