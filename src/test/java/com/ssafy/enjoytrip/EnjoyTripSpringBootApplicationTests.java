package com.ssafy.enjoytrip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;


//@SpringBootTest(
//	properties = {
//			"userId=euro",
//			"userPassword=1234"
//	}
//)
@SpringBootTest(
	properties = {"spring.config.location=classpath:application.properties"}
)
@Slf4j
class EnjoyTripSpringBootApplicationTests {
	
	@Value("${userId}")
	private String userId;
	
	@Value("${userPassword}")
	private String userPassword;

	@Test
	@DisplayName("Propertioes Test")
	void contextLoads() {
		log.debug(userId + " " + userPassword);
	}

}
