package com.ssafy.enjoytrip.board.model.service;

import java.util.List;

import com.ssafy.enjoytrip.board.model.dto.QnaBoard;

public interface QnaBoardService {
	List<QnaBoard> searchAll();
	List<QnaBoard> searchByArticleNo(int articleNo);
	List<QnaBoard> searchByQuestionArticleNo(int questionArticleNo);
	void delete(int articleNo);
}
