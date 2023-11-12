package com.ssafy.enjoytrip.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.dto.QnaBoard;

@Mapper
public interface QnaBoardDao {
	int totalQnaBoardCount();
	List<QnaBoard> searchList(int start, int size);
	QnaBoard searchQuestionBoard(int articleNo, int questionArticleNo);
	QnaBoard searchAnswerBoard(int articleNo, int questionArticleNo);
	void delete(int articleNo);
	int registAnswer(int articleNo, int questionArticleNo);
}
