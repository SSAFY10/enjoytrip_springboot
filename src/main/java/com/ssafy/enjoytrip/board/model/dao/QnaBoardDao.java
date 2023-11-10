package com.ssafy.enjoytrip.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.dto.QnaBoard;

@Mapper
public interface QnaBoardDao {
	List<QnaBoard> searchAll();
	List<QnaBoard> searchByArticleNo(int articleNo);
	List<QnaBoard> searchByQuestionArticleNo(int questionArticleNo);
	void delete(int articleNo);
}
