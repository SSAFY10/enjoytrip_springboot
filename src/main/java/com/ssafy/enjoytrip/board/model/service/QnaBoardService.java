package com.ssafy.enjoytrip.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.dto.QnaBoard;
import com.ssafy.enjoytrip.board.model.dto.QuestionAndAnswerDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface QnaBoardService {
	int totalQuestionCount();
	List<QnaBoard> questionList(PageNavigation pageNavigation);
	QuestionAndAnswerDto detailQnaBoard(int articleNo);
	int writeQuestion(QnaBoard qnaBoard);
	int writeAnswer(QnaBoard qnaBoard);
	int modify(QnaBoard qnaBoard);
	int delete(int articleNo);
}
