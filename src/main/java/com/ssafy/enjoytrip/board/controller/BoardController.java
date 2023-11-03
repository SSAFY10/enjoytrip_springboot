package com.ssafy.enjoytrip.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.dto.Board;
import com.ssafy.enjoytrip.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/board")
@AllArgsConstructor
@CrossOrigin(origins = { "" })
@Api(tags = { "Board Contorller API" })
public class BoardController {

	private BoardService service;
	private static final String SUCCESS = "success";

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "전체 조회", notes = "전체 게시글 조회")
	@ApiResponse(code = 200, message = SUCCESS)
	@GetMapping("/searchAll")
	public ResponseEntity<List> serachAll() {
		List<Board> boards = service.searchAll();
		return new ResponseEntity<List>(boards, HttpStatus.OK);
	}
	
	@ApiOperation(value = "조회", notes = "해당 게시글 조회")
	@ApiResponse(code = 200, message = SUCCESS)
	@GetMapping("/search")
	public ResponseEntity<Board> serach(String articelNo) {
		Board board = service.serach(articelNo);
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	@ApiOperation(value = "등록", notes = "게시글 작성")
	@ApiResponse(code = 200, message = SUCCESS)
	@PostMapping("/write")
	public ResponseEntity<Board> write(Board board) {
		service.write(board);
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	@ApiOperation(value = "수정", notes = "게시글 수정")
	@ApiResponse(code = 200, message = SUCCESS)
	@PutMapping("/modify")
	public ResponseEntity<Board> modify(Board board) {
		service.modify(board);
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	@ApiOperation(value = "삭제", notes = "게시글 삭제")
	@ApiResponse(code = 200, message = SUCCESS)
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(String articelNo) {
		service.delete(articelNo);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
