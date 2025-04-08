package com.springboot.aws.exception;

import lombok.Data;

@Data
public class InvalidRequestException extends RuntimeException {
	private String message;
	private String url;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1075465796606137389L;

	public InvalidRequestException (String message, String url) {
		super(message);
		this.message = message;
	}
}
