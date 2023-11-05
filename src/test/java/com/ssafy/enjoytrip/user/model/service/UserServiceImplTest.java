package com.ssafy.enjoytrip.user.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.user.model.dto.User;

import lombok.extern.slf4j.Slf4j;

//	Using Spring-Boot, and Lomboks @RequiredArgsConstructor, JUnit couldn't autowire the dependencies
@SpringBootTest(
		properties = {"spring.config.location=classpath:application.properties"}
	)
@Slf4j
@Transactional
class UserServiceImplTest {

	private final UserService userService;
	
	@Autowired
	public UserServiceImplTest(UserService userService) {
		this.userService = userService;
	}
	
	@Value("${testUserId}")
	String userId;
	@Value("${testUserPassword}")
	String userPassword;
	@Value("${testUserPhone}")
	String userPhone;	
	@Value("${testUserEmail}")
	String userEmail;

	@Value("${dummyUserId}")
	static String dummyUserId;	
	@Value("${dummyUserPassword}")
	static String dummyUserPassword;	
	@Value("${dummyUserPhone}")
	static String dummyUserPhone;
	@Value("${dummyUserEmail}")
	static String dummyUserEmail;
	
	public static Stream<Arguments> provideDummyUser() {
		User user = new User();
		user.setUserEmail(dummyUserEmail);
		user.setUserId(dummyUserId);
		user.setUserPassword(dummyUserPassword);
		user.setUserPhone(dummyUserPhone);
		
		return Stream.of(Arguments.of(user));
	}
	
	@BeforeEach
	void beforeEach() {
		log.info("unit test start");
	}
	
	@AfterEach
	void afterEach() {
		log.info("unit test end");
	}

	@Test
	void testLogin() {
		User user = userService.login(userId, userPassword);
		assertThat(user).isNotNull();
	}

	@ParameterizedTest
	@MethodSource("provideDummyUser")
	@DisplayName("test regist()")
	void testRegist(User user) {
		try {
			userService.regist(user);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("regist test fail: " + e.getMessage());
			fail("regist() Fail: " + e.getMessage());
		}
	}

	@ParameterizedTest
	@MethodSource("provideDummyUser")
	@DisplayName("test update()")
	void testUpdate(User user) {
		//given 
		user.setUserEmail("test@ssafy.com");
		
		try {
			userService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("update test fail: " + e.getMessage());
			fail("update() Fail: " + e.getMessage());
		}
	}
	
	@Test
	@DisplayName("test searchAll()")
	void testSearchAll() {
		List<User> userList = userService.searchAll();
		assertThat(userList).isNotNull();
	}

	@Test
	@DisplayName("test search()")
	void testSearch() {
		User user = userService.search(userId);
		assertThat(user).isNotNull();
	}

	@Test
	@DisplayName("test exit()")
	void testExit() {
		try {
			userService.exit(userId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exite test fail: " + e.getMessage());
			fail("exit() Fail: " + e.getMessage());
		}
	}

}
