package com.ssafy.enjoytrip.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.dao.BoardDao;
import com.ssafy.enjoytrip.board.model.dto.Board;
import com.ssafy.enjoytrip.board.model.dto.QuestionAndAnswerDto;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.SizeConstant;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.model.dao.QnaBoardDao;
import com.ssafy.enjoytrip.board.model.dto.QnaBoard;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QnaBoardServiceImpl implements QnaBoardService {

	private final QnaBoardDao qnaBoardDao;
	private final BoardDao boardDao;

	@Override
	public int totalQnaBoardCount() {
		return qnaBoardDao.totalQnaBoardCount();
	}

	@Override
	public List<QnaBoard> getList(PageNavigation pageNavigation) {
		int currentPage = pageNavigation.getCurrentPage();
		int sizePerPage = SizeConstant.LIST_SIZE;

		int start = (currentPage - 1) * sizePerPage;
		return qnaBoardDao.searchList(start, sizePerPage);
	}

	@Override
	public QuestionAndAnswerDto detailQnaBoard(int articleNo) {
		QnaBoard questionBoard =  qnaBoardDao.searchQuestionBoard(articleNo, -1);
		if (questionBoard != null && questionBoard.getBoard() != null) {
			int questionBoardId = Integer.parseInt(questionBoard.getBoard().getArticleNo());
			QnaBoard answerBoard = qnaBoardDao.searchAnswerBoard(-1, questionBoardId);
			if (answerBoard != null) {
				return new QuestionAndAnswerDto(questionBoard, answerBoard);
			}
		}
		return null;
	}

	@Override
	@Transactional
	public int writeAnswerBoard(QnaBoard answerBoard) {
		int questionBoardId = answerBoard.getQuestionArticleNo();
		Board answer = answerBoard.getBoard();
		boardDao.write(answer);
		return qnaBoardDao.registAnswer(Integer.parseInt(answer.getArticleNo()), questionBoardId);
	}

}
