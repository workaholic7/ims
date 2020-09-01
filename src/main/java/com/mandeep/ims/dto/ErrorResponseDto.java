package com.mandeep.ims.dto;

public class ErrorResponseDto {
	private String errorMessage;
	
	public ErrorResponseDto(String message) {
		this.errorMessage = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
