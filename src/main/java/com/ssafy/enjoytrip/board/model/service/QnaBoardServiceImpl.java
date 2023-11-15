package com.ssafy.enjoytrip.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.model.dao.QnaBoardDao;
import com.ssafy.enjoytrip.board.model.dto.QnaBoard;
import com.ssafy.enjoytrip.board.model.dto.QuestionAndAnswerDto;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.SizeConstant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class QnaBoardServiceImpl implements QnaBoardService {

	private final QnaBoardDao qnaBoardDao;

	@Override
	public int totalQuestionCount() {
		return qnaBoardDao.totalQuestionCount();
	}

	@Override
	public List<QnaBoard> questionList(PageNavigation pageNavigation) {
		int currentPage = pageNavigation.getCurrentPage();
		int sizePerPage = SizeConstant.LIST_SIZE;

		int start = (currentPage - 1) * sizePerPage;
		return qnaBoardDao.questionList(start, sizePerPage);
	}

	@Override
	public QuestionAndAnswerDto detailQnaBoard(int questionArticleNo) {
		QnaBoard question =  qnaBoardDao.searchQuestionBoard(questionArticleNo);
		List<QnaBoard> answers = qnaBoardDao.searchAnswerBoard(questionArticleNo);
		if (question != null && answers != null) {
			return new QuestionAndAnswerDto(question, answers);
		}
		return null;
	}
	
	@Override
	public int writeQuestion(QnaBoard qnaBoard) {
		return qnaBoardDao.registQuestion(qnaBoard);
	}

	@Override
	public int writeAnswer(QnaBoard qnaBoard) {
		return qnaBoardDao.registAnswer(qnaBoard);
	}

	@Override
	public int modify(QnaBoard qnaBoard) {
		return qnaBoardDao.modify(qnaBoard);
	}

	@Override
	public int delete(int articleNo) {
		return qnaBoardDao.delete(articleNo);
	}

}
