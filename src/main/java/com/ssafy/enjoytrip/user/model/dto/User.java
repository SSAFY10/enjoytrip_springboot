package com.ssafy.enjoytrip.user.model.dto;

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
public class User {
	@ApiParam(value="유저 아이디")
	String userId;			// admmin이 관리자
	@ApiParam(value="비밀번호")
	String userPassword;
	@ApiParam(value="전화번호")
	String userPhone;
	@ApiParam(value="이메일주소")
	String userEmail;
}
