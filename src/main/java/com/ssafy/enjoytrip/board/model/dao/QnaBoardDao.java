package com.ssafy.enjoytrip.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.dto.QnaBoard;

@Mapper
public interface QnaBoardDao {
	int totalQuestionCount();
	List<QnaBoard> questionList(int start, int sizePerPage);
	QnaBoard searchQuestionBoard(int articleNo);
	List<QnaBoard> searchAnswerBoard(int questionArticleNo);
	int registQuestion(QnaBoard qnaBoard);
	int registAnswer(QnaBoard qnaBoard);
	int modify(QnaBoard qnaBoard);
	int delete(int articleNo);
}
