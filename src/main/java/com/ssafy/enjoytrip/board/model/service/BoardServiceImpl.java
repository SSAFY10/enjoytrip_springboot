package com.ssafy.enjoytrip.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.model.dao.BoardDao;
import com.ssafy.enjoytrip.board.model.dto.Board;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private BoardDao dao;

	@Override
	public List<Board> searchAll() {
		return dao.searchAll();
	}

	@Override
	public Board serach(String articelNo) {
		return dao.serach(articelNo);
	}

	@Override
	public void write(Board board) {
		dao.write(board);
	}

	@Override
	public void modify(Board board) {
		dao.modify(board);
	}

	@Override
	public void delete(String articleNo) {
		dao.delete(articleNo);
	}

}
