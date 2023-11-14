package com.ssafy.enjoytrip.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.dto.QnaBoard;
import com.ssafy.enjoytrip.board.model.dto.QuestionAndAnswerDto;
import com.ssafy.enjoytrip.board.model.service.QnaBoardService;
import com.ssafy.enjoytrip.util.PageNavigation;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/qnaboard")
@RequiredArgsConstructor
@CrossOrigin(origins = { "*" })
@Api(tags = { "QnaBoard Controller API" })
public class QnaBoardController {
	private final QnaBoardService qnaBoardService;

	@ExceptionHandler
	private ResponseEntity<String> exceptionHandling(Exception e) {
		log.error("error: " + e.getMessage());
		e.printStackTrace();
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(e.getMessage());
	}

	@GetMapping
	public ResponseEntity<?> list(int pageNo) {
		int totalCount = qnaBoardService.totalQuestionCount();
		PageNavigation pageNavigation = PageNavigation.makePageNavigation(totalCount, pageNo, "");
		List<QnaBoard> qnaBoards = qnaBoardService.questionList(pageNavigation);
		
		if (qnaBoards != null && qnaBoards.size() > 0) {
			Map<String, Object> result = new HashMap<>();
			result.put("qnaBoards", qnaBoards);
			result.put("pageNavigation", pageNavigation);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(result);
		}
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(null);
	}

	@GetMapping("/{questionArticleNo}")
	public ResponseEntity<?> detailQnaBoard(@PathVariable int questionArticleNo) {
		QuestionAndAnswerDto result = qnaBoardService.detailQnaBoard(questionArticleNo);
		if (result != null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(result);
		}
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(null);
	}

	@PostMapping("/write/question")
	public ResponseEntity<?> writeQuestionBoard(@RequestBody QnaBoard qnaBoard) {
		if (qnaBoardService.writeQuestion(qnaBoard) == 1) {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(qnaBoard.getArticleNo());
		}
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(null);
	};

	@PostMapping("/write/answer")
	public ResponseEntity<?> writeAnswerBoard(@RequestBody QnaBoard qnaBoard) {
		if (qnaBoardService.writeAnswer(qnaBoard) == 1) {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(qnaBoard.getArticleNo());
		}
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(null);
	}

	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody QnaBoard qnaBoard) {
		if (qnaBoardService.modify(qnaBoard) == 1) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(qnaBoard.getArticleNo());
		}
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(null);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(int articleNo) {
		if (qnaBoardService.delete(articleNo) == 1) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(null);
		}
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(null);
	}
}
