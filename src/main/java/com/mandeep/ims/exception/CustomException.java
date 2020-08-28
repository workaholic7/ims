package com.mandeep.ims.exception;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(String message, Exception ex) {
		super(message, ex);
	}

	public CustomException() {
	}

}
