package com.cuit.boke.exception;

public class SaveFailException extends RuntimeException{

	private static final long serialVersionUID = 2880025133731365918L;

	public SaveFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public SaveFailException(String message) {
		super(message);
	}
	
	
}
