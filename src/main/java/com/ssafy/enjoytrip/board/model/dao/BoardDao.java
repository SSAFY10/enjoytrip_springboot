package com.ssafy.enjoytrip.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.dto.Board;

@Mapper
public interface BoardDao {
	public List<Board> searchAll();
	public Board search(String articleNo);
	public void write(Board board);
	public void modify(Board board);
	public void delete(String articleNo);
}
