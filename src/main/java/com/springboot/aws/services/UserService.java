package com.springboot.aws.services;

import java.util.List;
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
			throw new NotAvailableException("Email id already registered.", "/register");
		}

		Optional<User> userByMobile = findUserByMobile(user.getMobile());

		if (userByMobile.isPresent()) {
			throw new NotAvailableException("Mobile already registered.", "/register");
		}

		Optional<User> userByUserName = findUserByUserName(user.getUserName());

		if (userByUserName.isPresent()) {
			throw new NotAvailableException("User name already registered.", "/register");
		}

		return saveUser(user);
	}

	public List<User> findAllUser() {
		return userRepo.findAll();
	}

	public void deleteUser(User user) {
		userRepo.delete(user);
	}

	@CacheEvict(key = "user")
	public User saveUser (User user) {
		return userRepo.save(user);
	}

	@Cacheable(value="user", key = "#user.email")
	public Optional<User> findUserByEmail (String email) {
		return userRepo.findByEmail(email);
	}

	@Cacheable(value = "user", key = "#user.mobile")
	public Optional<User> findUserByMobile (String mobile) {
		return userRepo.findByMobile(mobile);
	}

	@Cacheable(value = "user", key = "#user.userName")
	public Optional<User> findUserByUserName (String userName) {
		return userRepo.findByUserName(userName);
	}
}
