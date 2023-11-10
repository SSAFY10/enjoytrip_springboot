package com.ssafy.enjoytrip.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.service.QnaBoardService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/qnaboard")
@RequiredArgsConstructor
@CrossOrigin(origins = { "" })
@Api(tags = { "QnaBoard Contorller API" })
public class QnaBoardController {
	private final QnaBoardService qnaBoardService;
	private static final String SUCCESS = "success";

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> search() {
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	public ResponseEntity<?> write() {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	public ResponseEntity<?> delete() {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	public ResponseEntity<?> modify() {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}
