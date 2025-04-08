package com.springboot.aws.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.aws.entity.User;
import com.springboot.aws.exception.InvalidRequestException;
import com.springboot.aws.services.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping(value="/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public User register(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	@GetMapping(value="/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> findAllUsers() {
		return userService.findAllUser();
	}

	@GetMapping(value="/users/{userBy}/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<User> findUser(@PathVariable String userBy, @PathVariable String value) {
		if (userBy.equalsIgnoreCase("email"))
			return userService.findUserByEmail(value);
		else if (userBy.equalsIgnoreCase("mobile"))
			return userService.findUserByMobile(value);
		else if (userBy.equalsIgnoreCase("userName"))
			return userService.findUserByUserName(value);
		else
			throw new InvalidRequestException (String.format("Invalid request, invalid userBy option: %s", userBy) , String.format("/users/%s/%s", userBy, value));
	}

	@DeleteMapping(value="/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUser(@RequestBody User user) {
		userService.deleteUser(user);
		return ResponseEntity.accepted().body("Request accepted.");
	}
}
