package com.springboot.aws.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.aws.entity.User;
import com.springboot.aws.exception.NotAvailableException;
import com.springboot.aws.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;

	public User registerUser(User user) {
		Optional<User> userByEmail = userRepo.findByEmail(user.getEmail());
		if (userByEmail.isPresent()) {
			throw new NotAvailableException("Email id already registered.");
		}
		Optional<User> userByMobile = userRepo.findByMobile(user.getMobile());
		if (userByMobile.isPresent()) {
			throw new NotAvailableException("Mobile already registered.");
		}

		return userRepo.save(user);
	}
}
