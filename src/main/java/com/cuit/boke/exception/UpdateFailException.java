package com.cuit.boke.exception;

public class UpdateFailException extends RuntimeException{

	private static final long serialVersionUID = -883550977761589284L;

	public UpdateFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public UpdateFailException(String message) {
		super(message);
	}

}
