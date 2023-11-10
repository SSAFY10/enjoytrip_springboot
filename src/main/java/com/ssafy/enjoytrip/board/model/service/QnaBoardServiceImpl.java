package com.ssafy.enjoytrip.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.model.dao.QnaBoardDao;
import com.ssafy.enjoytrip.board.model.dto.QnaBoard;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaBoardServiceImpl implements QnaBoardService {

	private final QnaBoardDao qnaBoardDao;
	
	@Override
	public List<QnaBoard> searchAll() {
		return qnaBoardDao.searchAll();
	}

	@Override
	public List<QnaBoard> searchByArticleNo(int articleNo) {
		return qnaBoardDao.searchByArticleNo(articleNo);
	}

	@Override
	public List<QnaBoard> searchByQuestionArticleNo(int questionArticleNo) {
		return qnaBoardDao.searchByQuestionArticleNo(questionArticleNo);
	}

	@Override
	public void delete(int articleNo) {
		qnaBoardDao.delete(articleNo);
	}

}
