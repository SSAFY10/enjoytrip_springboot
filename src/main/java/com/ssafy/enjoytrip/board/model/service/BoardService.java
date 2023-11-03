package com.ssafy.enjoytrip.board.model.service;

import java.util.List;

import com.ssafy.enjoytrip.board.model.dto.Board;

public interface BoardService {
	public List<Board> searchAll();
	public Board serach(String articelNo);
	public void write(Board board);
	public void modify(Board board);
	public void delete(String articleNo);
}
