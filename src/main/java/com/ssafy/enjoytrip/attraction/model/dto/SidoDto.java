package com.ssafy.enjoytrip.attraction.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SidoDto {
	@ApiParam(value = "시/도 코드")
	private int sidoCode;
	@ApiParam(value = "시/도 이름")
	private String sidoName;
}
