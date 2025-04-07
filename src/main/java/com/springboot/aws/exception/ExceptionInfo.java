package com.springboot.aws.exception;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ExceptionInfo {
	private String errorMessage;
	private String status;
	private Timestamp timestamp;
}
