package com.ssafy.enjoytrip.board.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaBoard {
	@ApiParam(value="게시글 번호")
	String articleNo;
	@ApiParam(value = "질문 게시글 번호")
	int questionArticleNo;
}
