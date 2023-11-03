package com.ssafy.enjoytrip.user.model.dto;

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
	String userId;			// admmin이 관리자
	String userPassword;
	String userPhone;
	String userEmail;
}
