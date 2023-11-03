package com.ssafy.enjoytrip.attraction.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GugunDto {
	@ApiParam(value = "구/군 코드")
	private int gugunCode;
	@ApiParam(value = "구/군 이름")
	private String gugunName;
	@ApiParam(value = "시/도 코드")
	private int sidoCode;
}
