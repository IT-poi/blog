package com.cuit.boke.exception;

public class DeleteFailException extends RuntimeException{

	private static final long serialVersionUID = -4434258903191690329L;

	public DeleteFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeleteFailException(String message) {
		super(message);
	}

	
}
