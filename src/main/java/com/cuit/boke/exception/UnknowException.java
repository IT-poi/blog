package com.cuit.boke.exception;

public class UnknowException extends RuntimeException{

	private static final long serialVersionUID = -3641526035799136629L;

	public UnknowException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknowException(String message) {
		super(message);
	}
	
}
