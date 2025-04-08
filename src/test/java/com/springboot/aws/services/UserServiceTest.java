package com.springboot.aws.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springboot.aws.entity.User;
import com.springboot.aws.exception.NotAvailableException;
import com.springboot.aws.repo.UserRepo;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepo userRepo;

	@BeforeEach
    void initService() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	void registerUserTest() {
		User user = mockUser();
		when(userRepo.save(any(User.class))).thenReturn(user);
		userService.registerUser(user);
		Mockito.verify(userRepo, Mockito.times(1)).save(Mockito.any(User.class));
	}

	@Test
	void registerUserTest_EmailExists() {
		User user = mockUser();
		when(userRepo.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
		Exception exception = assertThrows(NotAvailableException.class, () -> {
			userService.registerUser(user);
	    });
		assertEquals(exception.getMessage(), "Email id already registered.");
	}

	@Test
	void registerUserTest_MobileExists() {
		User user = mockUser();
		when(userRepo.findByMobile(Mockito.anyString())).thenReturn(Optional.of(user));
		Exception exception = assertThrows(NotAvailableException.class, () -> {
			userService.registerUser(user);
	    });
		assertEquals(exception.getMessage(), "Mobile already registered.");
	}

	private User mockUser() {
		User user = new User();
		user.setFirstName("JUnit");
		user.setLastName("Test");
		user.setAge(41);
		user.setEmail("userService@junit.com");
		user.setMobile("1234567890");
		user.setUserName("test@junit.com");
		return user;
	}
}
