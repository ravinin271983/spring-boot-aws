package com.springboot.aws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.aws.entity.User;
import com.springboot.aws.services.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping(value="/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public User register(@RequestBody User user) {
		return userService.registerUser(user);
	}
}
