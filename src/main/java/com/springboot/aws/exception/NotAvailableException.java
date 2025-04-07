package com.springboot.aws.exception;

import lombok.Data;

@Data
public class NotAvailableException extends RuntimeException {
	private String message;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1075465796606137389L;

	public NotAvailableException (String message) {
		super(message);
		this.message = message;
	}
}
