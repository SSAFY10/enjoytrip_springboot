package com.ssafy.enjoytrip.attraction.model.service;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoWithDistanceDto;
import com.ssafy.enjoytrip.attraction.model.dto.GugunDto;
import com.ssafy.enjoytrip.attraction.model.dto.SidoDto;
import com.ssafy.enjoytrip.util.PageNavigation;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(
		properties = {"spring.config.location=classpath:application.properties"}
	)
@Slf4j
@Transactional
class AttractionServiceImplTest {

	private AttractionService attractionService;
	
	@Autowired
	public AttractionServiceImplTest(AttractionService attractionService) {
		this.attractionService = attractionService;
	}
	
	@Value("${testTitle}")
	String title;
	@Value("${testSidoCode}")
	static int sidoCode;
	@Value("${testGugunCode}")
	static int gugunCode;
	@Value("${testContentTypeId}")
	static int contentTypeId;
	
	static Stream<Arguments> provideDummyAttraction() {
		AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
		attractionInfoDto.setSidoCode(sidoCode);
		attractionInfoDto.setGugunCode(gugunCode);
		attractionInfoDto.setContentId(contentTypeId);
		
		return Stream.of(Arguments.of(attractionInfoDto));
	}
	
	static Stream<Arguments> provideDummyAttractionAndPageNavigation() {
		AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
		attractionInfoDto.setSidoCode(sidoCode);
		attractionInfoDto.setGugunCode(gugunCode);
		attractionInfoDto.setContentId(contentTypeId);
		
		PageNavigation pageNavigation = new PageNavigation();
		
		return Stream.of(Arguments.of(attractionInfoDto, pageNavigation));
	}
	
	@ParameterizedTest
	@MethodSource("provideDummyAttraction")
	void testTotalAttractionListCount(AttractionInfoDto attractionInfoDto) {
		int count = attractionService.totalAttractionListCount(attractionInfoDto);
		assertThat(count).isGreaterThan(-1);
	}

	@ParameterizedTest
	@MethodSource("provideDummyAttraction")
	void testSearchAttractionInfoDto(AttractionInfoDto attractionInfoDto) {
		List<AttractionInfoDto> attractions = attractionService.search(attractionInfoDto);
		assertThat(attractions).isNotNull();
	}

	@ParameterizedTest
	@MethodSource("provideDummyAttractionAndPageNavigation")
	void testSearchAttractionInfoDtoPageNavigation(AttractionInfoDto attractionInfoDto, PageNavigation pageNavigation) {
		// given 
		int currentPage = 1;
		pageNavigation.setCurrentPage(currentPage);
		
		// when 
		List<AttractionInfoDto> attractions = attractionService.search(attractionInfoDto, pageNavigation);
		
		// then
		assertThat(attractions).isNotNull();
	}

	@Test
	void testSearchByTitle() {
		List<AttractionInfoDto> attractions = attractionService.searchByTitle(title, sidoCode);
		assertThat(attractions).isNotNull();
	}

	@ParameterizedTest
	@MethodSource("provideDummyAttraction")
	void testSortByDistance(AttractionInfoDto attractionInfoDto) {
		// given
		int distance = 500;
		double latitude = 37.501328668708;
		double longitude = 127.03953821497;
		attractionInfoDto.setLatitude(latitude);
		attractionInfoDto.setLongitude(longitude);
		
		// when
		List<AttractionInfoWithDistanceDto> attractions = attractionService.sortByDistance(attractionInfoDto, distance);
		
		// then
		assertThat(attractions).isNotNull();
	}

	@Test
	void testSearchAllSido() {
		List<SidoDto> sidos = attractionService.searchAllSido();
		assertThat(sidos).isNotNull();
	}

	@Test
	void testSearchGugunBySido() {
		List<GugunDto> guguns = attractionService.searchGugunBySido(sidoCode);
		assertThat(guguns).isNotNull();
	}

}
