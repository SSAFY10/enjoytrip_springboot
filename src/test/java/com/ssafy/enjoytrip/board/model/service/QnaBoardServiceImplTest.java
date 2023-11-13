package com.ssafy.enjoytrip.board.model.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.ssafy.enjoytrip.board.model.dto.QnaBoard;
import com.ssafy.enjoytrip.board.model.dto.QuestionAndAnswerDto;
import com.ssafy.enjoytrip.provider.DummyAnswerBoardProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(
        properties = {"spring.config.location=classpath:application.properties"}
)
@Slf4j
@Transactional
class QnaBoardServiceImplTest {
    private final QnaBoardService qnaBoardService;

    @Value("${testQnaBoardArticleNo}")
    int articleNo;
    @Value("${testEmptyQnaBoardArticleNo}")
    int questionArticleNo;
    @Value("${testUserId}")
    String userId;
    @Value("${dummyAnswerBoardTitle}")
    String dummyAnswerBoardTitle;
    @Value("${dummyAnswerBoardContent}")
    String dummyAnswerBoardContent;

    @Autowired
    public QnaBoardServiceImplTest(QnaBoardService qnaBoardService) {
        this.qnaBoardService = qnaBoardService;
    }

    @BeforeEach
    void beforeEach() {
        log.info("unit test start");
    }

    @AfterEach
    void afterEach() {
        log.info("unit test end");
    }

    @Test
    void detailQnaBoard() {
        QuestionAndAnswerDto result = qnaBoardService.detailQnaBoard(articleNo);
        assertThat(result).isNotNull();
        QnaBoard question = result.getQuestion();
        assertThat(question).isNotNull();
        log.info("question: " + question);
        QnaBoard answer= result.getAnswer();
        log.info("answer: " + answer);
    }

    @ParameterizedTest
    @ArgumentsSource(DummyAnswerBoardProvider.class)
    void writeAnswerBoard(QnaBoard answerBoard) {
        log.info("answerBoard: " + answerBoard);
        log.info(dummyAnswerBoardTitle);
        log.info(dummyAnswerBoardContent);
        int result = qnaBoardService.writeAnswerBoard(answerBoard);
        assertThat(result).isOne();
    }
}