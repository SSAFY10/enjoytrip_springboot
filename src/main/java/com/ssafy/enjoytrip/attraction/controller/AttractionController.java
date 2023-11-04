package com.ssafy.enjoytrip.attraction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoWithDistanceDto;
import com.ssafy.enjoytrip.attraction.model.dto.GugunDto;
import com.ssafy.enjoytrip.attraction.model.dto.SidoDto;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;
import com.ssafy.enjoytrip.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/attraction")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = {""})
@Api(tags = {"Attraction Contorller API"})
public class AttractionController {
	private final AttractionService attractionService;
	private static final String SUCCESS = "SUCCESS";
	private static final String NO_CONTENT = "NO_CONTENT";
	
	@ExceptionHandler
	private ResponseEntity<String> exceptionHandling(Exception e) {
		log.error("error: " + e.getMessage());
		e.printStackTrace();
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(e.getMessage());
	}
	
	@ApiOperation(value = "관광지 목록 (최단거리순)", notes = "현재 위치로부터 가장 가까운 관광지 목록들")
	@ApiResponses({
		@ApiResponse(code = 200, message = SUCCESS, responseContainer = "Map"),
		@ApiResponse(code = 204, message = NO_CONTENT)})
	@GetMapping("/sort")
	public ResponseEntity<?> sort(
			@ApiParam(value = "시/도 코드", required = true)
			@RequestParam
			int sidoCode,
			
			@ApiParam(value = "구/군 코드", required = true)
			@RequestParam
			int gugunCode,
			
			@ApiParam(value = "컨텐트 타입 아이디", required = true)
			@RequestParam
			int contentTypeId,
			
			@ApiParam(value = "페이지 넘버", defaultValue = "1")
			@RequestParam(defaultValue = "1")
			int pageNo,
			
			@ApiParam(value = "현재 위치로부터의 거리", defaultValue = "500")
			@RequestParam(defaultValue = "500") 
			int distance,
			
			@ApiParam(value = "위도(가로선)", defaultValue = "37.501328668708")
			@RequestParam(defaultValue = "37.501328668708")
			double latitude,
			
			@ApiParam(value = "경도(세로선)", defaultValue = "127.03953821497")
			@RequestParam(defaultValue = "127.03953821497")
			double longitude) {
		
		AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
		attractionInfoDto.setSidoCode(sidoCode);
		attractionInfoDto.setGugunCode(gugunCode);
		attractionInfoDto.setContentTypeId(contentTypeId);
		attractionInfoDto.setLatitude(latitude);
		attractionInfoDto.setLongitude(longitude);
		
		int totalCount = attractionService.totalAttractionListCount(attractionInfoDto);
		
		List<AttractionInfoWithDistanceDto> sortedAttractionList = attractionService.sortByDistance(attractionInfoDto, distance);
		log.info("result: " + sortedAttractionList);
		
		if (sortedAttractionList != null && !sortedAttractionList.isEmpty()) {
			PageNavigation pageNavigation = PageNavigation.makePageNavigation(totalCount, pageNo, "/sort/" + pageNo);
			Map<String, Object> response = new HashMap<>();
			response.put("sortedAttractionList", sortedAttractionList);
			response.put("pageNavigation", pageNavigation);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(response);
		}
		
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(null);
	}
	
	@ApiOperation(value = "구/군 목록", notes = "구/군 목록 조회 시 시/도 코드 필요")
	@ApiResponses({
		@ApiResponse(code = 200, message = SUCCESS, responseContainer = "List"),
		@ApiResponse(code = 204, message = NO_CONTENT)})
	@GetMapping("/gugun/{sidoCode}")
	public ResponseEntity<List<GugunDto>> gugun(
			@ApiParam(value = "시/도 코드", required = true)
			@PathVariable
			int sidoCode) {
		
		List<GugunDto> gugunList = attractionService.searchGugunBySido(sidoCode);
		
		if (gugunList != null && !gugunList.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(gugunList);
		}
		
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(null);
	}
	
	
	@ApiOperation(value = "시/도 목록", notes = "시/도 목록")
	@ApiResponses({
		@ApiResponse(code = 200, message = SUCCESS, responseContainer = "List"),
		@ApiResponse(code = 204, message = NO_CONTENT)})
	@GetMapping("/sido")
	public ResponseEntity<List<SidoDto>> sido() {
		
		List<SidoDto> sidoList = attractionService.searchAllSido();
		
		if (sidoList != null && !sidoList.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(sidoList);
		}
		
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(null); 
	}
	
	@ApiOperation(value = "관광지 목록", notes = "페이지 숫자, 시/도 및 구/군 코드와 contentTypeId 필요")
	@ApiResponses({
		@ApiResponse(code = 200, message = SUCCESS, responseContainer = "Map"),
		@ApiResponse(code = 204, message = NO_CONTENT)})
	@GetMapping("/list")
	public ResponseEntity<?> list(
			@ApiParam(value = "시/도 코드", required = true)
			@RequestParam
			int sidoCode,
			
			@ApiParam(value = "구/군 코드", required = true)
			@RequestParam
			int gugunCode,
			
			@ApiParam(value = "컨텐트 타입 아이디", required = true)
			@RequestParam
			int contentTypeId,
			
			@ApiParam(value = "페이지 넘버", required = true)
			@RequestParam(defaultValue = "1") 
			int pageNo) {
		
		AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
		attractionInfoDto.setSidoCode(sidoCode);
		attractionInfoDto.setGugunCode(gugunCode);
		attractionInfoDto.setContentTypeId(contentTypeId);
		
		int totalCount = attractionService.totalAttractionListCount(attractionInfoDto);
		PageNavigation pageNavigation = PageNavigation.makePageNavigation(totalCount, pageNo, "/find/" + pageNo);
		List<AttractionInfoDto> attractionList = attractionService.searchWithPaging(attractionInfoDto, pageNavigation);
		
		if (attractionList != null && !attractionList.isEmpty()) {
			Map<String, Object> response = new HashMap<>();
			response.put("attractionList", attractionList);
			response.put("pageNavigation", pageNavigation);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(response);
		}
		
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(null); 
	}
	
}
