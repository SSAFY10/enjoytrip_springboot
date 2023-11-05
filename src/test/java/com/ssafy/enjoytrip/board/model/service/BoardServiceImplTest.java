package com.ssafy.enjoytrip.board.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.board.model.dto.Board;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(
		properties = {"spring.config.location=classpath:application.properties"}
	)
@Slf4j
@Transactional
class BoardServiceImplTest {
	
	private final BoardService boardService;
	
	@Autowired
	public BoardServiceImplTest(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Value("${testArticleNo}")
	String articleNo;
	
	@Value("${testUserId}")
	static String userId;
	@Value("${dummyBoardTitle}")
	static String dummyBoardTitle;
	@Value("${dummyBoardContent}")
	static String dummyBoardContent;
	
	public static Stream<Arguments> provideDummyBoard() {
		Board board = new Board();
		board.setUserId(userId);
		board.setTitle(dummyBoardTitle);
		board.setContent(dummyBoardContent);
		
		return Stream.of(Arguments.of(board));
	}
	
	@BeforeEach
	void beforeEach() {
		log.info("unit test start");
	}
	
	@AfterEach
	void afterEach() {
		log.info("unit test end");
	}

	@ParameterizedTest
	@MethodSource("provideDummyBoard")
	void testWrite(Board board) {
		try {
			boardService.write(board);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("write test fail: " + e.getMessage());
			fail("write() fail: " + e.getMessage());
		}
	}

	@ParameterizedTest
	@MethodSource("provideDummyBoard")
	void testModify(Board board) {
		//given
		board.setArticleNo(articleNo);
		board.setContent("test success");
		
		try {
			boardService.modify(board);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("modify test fail: " + e.getMessage());
			fail("modify() fail: " + e.getMessage());
		}
	}

	@Test
	void testDelete() {
		try {
			boardService.delete(articleNo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("delete test fail: " + e.getMessage());
			fail("delete() fail: " + e.getMessage());
		}
	}

	@Test
	void testSearchAll() {
		List<Board> boardList = boardService.searchAll();
		assertThat(boardList).isNotNull();
	}

	@Test
	void testSearch() {
		Board board = boardService.search(articleNo);
		assertThat(board).isNotNull();
	}

}
