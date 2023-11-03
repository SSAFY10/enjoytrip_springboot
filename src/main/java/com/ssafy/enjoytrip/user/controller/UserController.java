package com.ssafy.enjoytrip.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.model.dto.User;
import com.ssafy.enjoytrip.user.model.service.UserService;
import com.ssafy.enjoytrip.util.PwdHash;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = {""})
@Api(tags = {"User Contorller API"})
public class UserController {

	private UserService service;
	private PwdHash pwdhash;
	private static final String SUCCESS = "success";
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "로그인", notes = "로그인")
	@ApiResponse(code = 200, message = SUCCESS)
	@PostMapping("/login")
	public ResponseEntity<?> login(String user_id, String user_password, HttpSession session) {
		String hashPwd = pwdhash.getHasing(user_id, user_password);
		User user = service.login(user_id, hashPwd);
		session.setAttribute("userInfo", user);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "로그아웃", notes = "로그아웃")
	@ApiResponse(code = 200, message = SUCCESS)
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업데이트", notes = "회원 정보 업데이트")
	@ApiResponse(code = 200, message = SUCCESS)
	@PutMapping
	public ResponseEntity<String> update(User user) {
		String hashPwd = pwdhash.getHasing(user.getUserId(), user.getUserPassword());
		user.setUserPassword(hashPwd);
		service.update(user);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "등록", notes = "회원 정보 등록")
	@ApiResponse(code = 200, message = SUCCESS)
	@PostMapping
	public ResponseEntity<String> regist(User user) {
		String hashPwd = pwdhash.getHasing(user.getUserId(), user.getUserPassword());
		user.setUserPassword(hashPwd);
		service.regist(user);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "탈퇴", notes = "회원 탈퇴")
	@ApiResponse(code = 200, message = SUCCESS)
	@DeleteMapping
	public ResponseEntity<String> exit(String user_id) {
		service.exit(user_id);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
}
