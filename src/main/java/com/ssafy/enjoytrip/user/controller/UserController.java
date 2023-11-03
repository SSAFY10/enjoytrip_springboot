package com.ssafy.enjoytrip.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.model.dto.User;
import com.ssafy.enjoytrip.user.model.service.UserService;
import com.ssafy.enjoytrip.util.PwdHash;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	private UserService service;
	private PwdHash pwdhash;
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(String user_id, String user_password, HttpSession session) {
		String hashPwd = pwdhash.getHasing(user_id, user_password);
		User user = service.login(user_id, hashPwd);
		session.setAttribute("userInfo", user);
		return "redircet:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping
	public String update() {
		return "user/update";
	}
	
	@PutMapping
	public String update(User user) {
		String hashPwd = pwdhash.getHasing(user.getUserId(), user.getUserPassword());
		user.setUserPassword(hashPwd);
		service.update(user);
		// 회원 정보 보여주는 창으로 이동
		return "redirect:/user/info";
	}
	
//	@GetMapping
//	public String regist() {
//		return "user/regist";
//	}
	
	@PostMapping
	public String regist(User user) {
		String hashPwd = pwdhash.getHasing(user.getUserId(), user.getUserPassword());
		user.setUserPassword(hashPwd);
		service.regist(user);
		// 메인 페이지로 이동
		return "redirect:/";
	}
	
	@DeleteMapping
	public String exit(String user_id) {
		service.exit(user_id);
		// 메인 페이지로 이동
		return "redirect:/";
	}
	
}
