package com.springboot.aws.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.aws.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	public Optional<User> findByEmail(String email);
	public Optional<User> findByMobile(String mobile);
}