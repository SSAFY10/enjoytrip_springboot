package com.ssafy.enjoytrip.user.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.dto.User;

@Mapper
public interface UserDao {
	public List<User> searchAll();
	public User search(String userId);
	public void insert(User user);
	public void update(User user);
	public void delete(String userId);
}
