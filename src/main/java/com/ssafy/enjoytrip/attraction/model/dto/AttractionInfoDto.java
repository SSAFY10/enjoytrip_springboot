package com.ssafy.enjoytrip.attraction.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttractionInfoDto {
	@ApiParam(value = "컨텐트 아이디")
	private int contentId;
	@ApiParam(value = "타입 아이디")
	private int contentTypeId;
	@ApiParam(value = "제목")
	private String title;
	@ApiParam(value = "주소")
	private String addr1;
	@ApiParam(value = "상세 주소")
	private String addr2;
	@ApiParam(value = "지번")
	private String zipcode;
	@ApiParam(value = "연락처")
	private String tel;
	@ApiParam(value = "이미지 파일1")
	private String firstImage;
	@ApiParam(value = "이미지 파일2")
	private String firstImage2;
	@ApiParam(value = "readcount")
	private int readcount;
	@ApiParam(value = "시/도 코드")
	private int sidoCode;
	@ApiParam(value = "구/군 코드")
	private int gugunCode;
	@ApiParam(value = "위도(가로선)")
	private double latitude;
	@ApiParam(value = "경도(세로선)")
	private double longitude;
	@ApiParam(value = "mlevel")
	private String mlevel;
}
