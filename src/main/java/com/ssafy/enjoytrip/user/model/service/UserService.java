package com.ssafy.enjoytrip.user.model.service;

import java.util.List;

import com.ssafy.enjoytrip.user.model.dto.User;

public interface UserService {
	public List<User> searchAll();
	public User search(String userId);
	public User login(String userId, String userPassword);
	public void regist(User user);
	public void update(User user);
	public void exit(String userId);
}
