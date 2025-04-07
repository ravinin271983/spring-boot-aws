package com.springboot.aws.config;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springboot.aws.exception.ExceptionInfo;
import com.springboot.aws.exception.NotAvailableException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApplicationExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NotAvailableException.class)
	@ResponseBody
	public ExceptionInfo notAvailable(HttpServletRequest req, NotAvailableException exception) {
		ExceptionInfo info = new ExceptionInfo();
		info.setErrorMessage(exception.getMessage());
		info.setStatus(Integer.toString(HttpStatus.BAD_REQUEST.value()));
		Instant now = Instant.now();
		info.setTimestamp(Timestamp.from(now));
		return info;
	}
}
