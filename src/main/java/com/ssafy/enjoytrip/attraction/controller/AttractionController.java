package com.ssafy.enjoytrip.attraction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoWithDistanceDto;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;
import com.ssafy.enjoytrip.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "책 정보 등록", notes = "도서 정보 등록")
	@ApiResponse(code = 200, message = SUCCESS)
	@GetMapping("/sort/{pageNo}")
	public ResponseEntity<?> sort(
			AttractionInfoDto attractionInfoDto,
			@PathVariable int pageNo,
			@RequestParam(defaultValue = "500") int distance,
			@RequestParam(required = true) double latitude,
			@RequestParam(required = true) double longitude) {
		
		try {
			
			List<AttractionInfoWithDistanceDto> sortedAttractionList = attractionService.sortByDistance(attractionInfoDto, distance);
			
			if (sortedAttractionList != null && !sortedAttractionList.isEmpty()) {
				PageNavigation pageNavigation = PageNavigation.makePageNavigation(sortedAttractionList.size(), pageNo, "/sort/" + pageNo);
				Map<String, Object> response = new HashMap<>();
				response.put("sortedAttractionList", sortedAttractionList);
				response.put("pageNavigation", pageNavigation);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			else {
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}
		}
		catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
}
