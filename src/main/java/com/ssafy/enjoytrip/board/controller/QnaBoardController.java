package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.model.dto.Board;
import com.ssafy.enjoytrip.board.model.dto.QnaBoard;
import com.ssafy.enjoytrip.board.model.dto.QuestionAndAnswerDto;
import com.ssafy.enjoytrip.util.Page;
import com.ssafy.enjoytrip.util.PageNavigation;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.enjoytrip.board.model.service.QnaBoardService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/qnaboard")
@RequiredArgsConstructor
@CrossOrigin(origins = { "*" })
@Api(tags = { "QnaBoard Controller API" })
public class QnaBoardController {
	private final QnaBoardService qnaBoardService;
	private static final String SUCCESS = "success";

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
		int totalCount = qnaBoardService.totalQnaBoardCount();
		PageNavigation pageNavigation = PageNavigation.makePageNavigation(totalCount, pageNo, "");

		List<QnaBoard> qnaBoards = qnaBoardService.getList(pageNavigation);

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
	public ResponseEntity<Board> writeQuestionBoard(Board board, HttpServletRequest request) {
		return ResponseEntity
				.status(HttpStatus.TEMPORARY_REDIRECT)
				.location(ServletUriComponentsBuilder
						.fromContextPath(request)
						.path("/board")
						.path("/write")
						.build()
						.toUri())
				.body(board);
	};

	@PostMapping("/write/answer")
	public ResponseEntity<Void> writeAnswerBoard(QnaBoard qnaBoard) {
		if (qnaBoardService.writeAnswerBoard(qnaBoard) == 1) {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(null);
		}
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(null);
	}

	@PutMapping("/modify")
	public ResponseEntity<Board> modify(Board board) {
		return ResponseEntity
				.status(HttpStatus.TEMPORARY_REDIRECT)
				.location(URI.create("/board/modify"))
				.body(board);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Integer> delete(int articleNo) {
		return ResponseEntity
				.status(HttpStatus.TEMPORARY_REDIRECT)
				.location(URI.create("/board/delete"))
				.body(articleNo);
	}
}
