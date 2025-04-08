package com.springboot.aws.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application-test.properties")
public class ControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void helloTest() throws Exception {
		this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void registerUserTest() throws Exception {
		String content="{\r\n"
				+ "    \"userName\": \"test@junit\",\r\n"
				+ "	\"firstName\": \"JUnit\", \r\n"
				+ "    \"lastName\": \"Test\", \r\n"
				+ "    \"email\": \"abc@test.com\", \r\n"
				+ "    \"mobile\": \"1234567890\", \r\n"
				+ "    \"age\": \"41\"\r\n"
				+ "	\r\n"
				+ "}";
		this.mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(content)).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void registerDuplicateEmailTest() throws Exception {
		String content="{\r\n"
				+ "    \"userName\": \"test2@junit\",\r\n"
				+ "	\"firstName\": \"JUnit\", \r\n"
				+ "    \"lastName\": \"Test\", \r\n"
				+ "    \"email\": \"abc@test.com\", \r\n"
				+ "    \"mobile\": \"1234567891\", \r\n"
				+ "    \"age\": \"41\"\r\n"
				+ "	\r\n"
				+ "}";
		this.mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(content)).andDo(print())
			.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
			.andExpect(content().string(containsString("Email id already registered.")));
	}

	@Test
	void registerDuplicateMobileTest() throws Exception {
		String content="{\r\n"
				+ "    \"userName\": \"test3@junit\",\r\n"
				+ "	\"firstName\": \"JUnit\", \r\n"
				+ "    \"lastName\": \"Test\", \r\n"
				+ "    \"email\": \"abc-mobile@test.com\", \r\n"
				+ "    \"mobile\": \"1234567890\", \r\n"
				+ "    \"age\": \"41\"\r\n"
				+ "	\r\n"
				+ "}";
		this.mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(content)).andDo(print())
			.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
			.andExpect(content().string(containsString("Mobile already registered.")));
	}
	
	@Test
	void registerDuplicateUserNameTest() throws Exception {
		String content="{\r\n"
				+ "    \"userName\": \"test@junit\",\r\n"
				+ "	\"firstName\": \"JUnit\", \r\n"
				+ "    \"lastName\": \"Test\", \r\n"
				+ "    \"email\": \"abc4@test.com\", \r\n"
				+ "    \"mobile\": \"1234567894\", \r\n"
				+ "    \"age\": \"41\"\r\n"
				+ "	\r\n"
				+ "}";
		this.mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(content)).andDo(print())
			.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
			.andExpect(content().string(containsString("User name already registered.")));
	}

	@Test
	void findAllTest() throws Exception {
		this.mockMvc.perform(get("/users")).andDo(print())
			.andExpect(status().is(HttpStatus.OK.value()));
	}
	
	@Test
	void findByEmail() throws Exception {
		this.mockMvc.perform(get("/users/email/user1-email@junit.test")).andDo(print())
			.andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(content().string(containsString("user-name1@junit.test")))
			.andExpect(content().string(containsString("1111111111")));
	}

	@Test
	void findByMobile() throws Exception {
		this.mockMvc.perform(get("/users/mobile/1111111111")).andDo(print())
			.andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(content().string(containsString("user-name1@junit.test")))
			.andExpect(content().string(containsString("user1-email@junit.test")));;
	}
	
	@Test
	void findByUserName() throws Exception {
		this.mockMvc.perform(get("/users/userName/user-name1@junit.test")).andDo(print())
			.andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(content().string(containsString("1111111111")))
			.andExpect(content().string(containsString("user1-email@junit.test")));;
	}

	@Test
	void findByUser_invalidOption() throws Exception {
		this.mockMvc.perform(get("/users/xyz/user-name1@junit.test")).andDo(print())
			.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
			.andExpect(content().string(containsString("Invalid request, invalid userBy option: xyz")));
	}
	
	@Test
	void deleteUser() throws Exception {
		String content="{\r\n"
				+ "    \"userName\": \"test@junit\",\r\n"
				+ "	\"firstName\": \"JUnit\", \r\n"
				+ "    \"lastName\": \"Test\", \r\n"
				+ "    \"email\": \"abc@test.com\", \r\n"
				+ "    \"mobile\": \"1234567890\", \r\n"
				+ "    \"age\": \"41\"\r\n"
				+ "	\r\n"
				+ "}";
		this.mockMvc.perform(delete("/users").contentType(MediaType.APPLICATION_JSON).content(content)).andDo(print())
			.andExpect(status().is(HttpStatus.ACCEPTED.value()))
			.andExpect(content().string(containsString("Request accepted.")));
	}
}
