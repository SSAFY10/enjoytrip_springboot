package com.ssafy.enjoytrip.provider;

import com.ssafy.enjoytrip.board.model.dto.Board;
import com.ssafy.enjoytrip.board.model.dto.QnaBoard;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.stream.Stream;

@Slf4j
@ToString
public class DummyAnswerBoardProvider implements ArgumentsProvider {
    int questionArticleNo = 9;
    String userId = "ssafy";
    String dummyAnswerBoardTitle = "Answer!";
    String dummyAnswerBoardContent = "answered complete";

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        log.info(String.valueOf(this));
        Board answer = new Board();
        answer.setUserId(userId);
        answer.setTitle(dummyAnswerBoardTitle);
        answer.setContent(dummyAnswerBoardContent);

        QnaBoard answerBoard = new QnaBoard();
        answerBoard.setBoard(answer);
        answerBoard.setQuestionArticleNo(questionArticleNo);
        log.info("provide answerBoard: " + answerBoard);
        return Stream.of(Arguments.of(answerBoard));
    }

}
