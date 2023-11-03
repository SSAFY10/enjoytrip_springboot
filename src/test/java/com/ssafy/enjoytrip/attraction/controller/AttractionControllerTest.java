package com.ssafy.enjoytrip.attraction.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.attraction.model.dto.Attraction;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;

import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@SpringBootTest(
	properties = {"spring.config.location=classpath:application.properties"},
	classes = { AttractionController.class }
)
@Slf4j
@ComponentScan(basePackages = {"com.ssafy"})
@ContextConfiguration(classes = AttractionController.class)
class AttractionControllerTest {
	@Value("${userId}")
	private String userId;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("mockMvc Test")
	@Transactional
	void testToString() throws Exception {
		log.debug("---mockMvc Test Start---");
		Attraction attraction = new Attraction();
		String content = new ObjectMapper().writeValueAsString(attraction);
		
		mockMvc.perform(get("/address/user" + userId).content(content).contentType(MediaType.APPLICATION_JSON)) // post, put, delete
			.andExpect(status().isOk()) // isCreated(), is2xxSuccessful()
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.userName", is(userId)))
			.andDo(print());
		log.debug("---mockMvc Test End---");
	}
	
	@Test
	@Disabled
	void testDisabled() {
		fail("Not yet implemented");
	}
}
