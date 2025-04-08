package com.springboot.aws.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springboot.aws.entity.User;
import com.springboot.aws.exception.NotAvailableException;
import com.springboot.aws.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;

	public User registerUser(User user) {
		Optional<User> userByEmail = findUserByEmail(user.getEmail());

		if (userByEmail.isPresent()) {
			throw new NotAvailableException("Email id already registered.");
		}

		Optional<User> userByMobile = findUserByMobile(user.getMobile());

		if (userByMobile.isPresent()) {
			throw new NotAvailableException("Mobile already registered.");
		}

		return saveUser(user);
	}

	@CacheEvict(key = "user")
	public User saveUser (User user) {
		return userRepo.save(user);
	}

	@Cacheable(key = "user")
	public Optional<User> findUserByEmail (String email) {
		return userRepo.findByEmail(email);
	}

	@Cacheable(key = "user")
	public Optional<User> findUserByMobile (String mobile) {
		return userRepo.findByMobile(mobile);
	}

}
