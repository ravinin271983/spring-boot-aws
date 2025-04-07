package com.springboot.aws;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "/application-test.properties")
public class SpringApplicationTest {
	@Test
	public void contextLoad() {
		
	}
}
