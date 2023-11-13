package com.ssafy.enjoytrip.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin(origins = { "*" })
@Api(tags = { "Board Controller API" })
public class BoardController {

	private BoardService service;
	private static final String SUCCESS = "success";

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "전체 조회", notes = "전체 게시글 조회")
	@ApiResponse(code = 200, message = SUCCESS)
	@GetMapping("/searchAll")
	public ResponseEntity<List<Board>> searchAll() {
		List<Board> boards = service.searchAll();
		return new ResponseEntity<>(boards, HttpStatus.OK);
	}
	
	@ApiOperation(value = "조회", notes = "해당 게시글 조회")
	@ApiResponse(code = 200, message = SUCCESS)
	@GetMapping("/search")
	public ResponseEntity<Board> search(String articleNo) {
		Board board = service.search(articleNo);
		return new ResponseEntity<>(board, HttpStatus.OK);
	}

	@ApiOperation(value = "등록", notes = "게시글 작성")
	@ApiResponse(code = 200, message = SUCCESS)
	@PostMapping("/write")
	public ResponseEntity<Board> write(@RequestBody Board board) {
		service.write(board);
		return new ResponseEntity<>(board, HttpStatus.OK);
	}

	@ApiOperation(value = "수정", notes = "게시글 수정")
	@ApiResponse(code = 200, message = SUCCESS)
	@PutMapping("/modify")
	public ResponseEntity<Board> modify(@RequestBody Board board) {
		service.modify(board);
		return new ResponseEntity<>(board, HttpStatus.OK);
	}

	@ApiOperation(value = "삭제", notes = "게시글 삭제")
	@ApiResponse(code = 200, message = SUCCESS)
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(String articleNo) {
		service.delete(articleNo);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
}
