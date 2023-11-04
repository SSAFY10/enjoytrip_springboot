package com.ssafy.enjoytrip.board.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
	@ApiParam(value="게시글 번호")
	String articleNo;
	@ApiParam(value="작성자 아이디")
	String userId;
	@ApiParam(value="게시글 제목")
	String title;
	@ApiParam(value="게시글 내용")
	String content;
	@ApiParam(value="조회수")
	String hit;
	@ApiParam(value="작성시간")
	String registerTime;
}
