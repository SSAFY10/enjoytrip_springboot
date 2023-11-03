package com.ssafy.enjoytrip.user.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.model.dao.UserDao;
import com.ssafy.enjoytrip.user.model.dto.User;
import com.ssafy.enjoytrip.user.model.dto.UserException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserDao dao;
	
	@Override
	public List<User> searchAll() {
		return dao.searchAll();
	}

	@Override
	public User search(String userId) {
		return dao.search(userId);
	}

	@Override
	public User login(String userId, String userPassword) {
		User user = dao.search(userId);
		if (user == null) {
			throw new UserException("아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
		} else {
			if (user.getUserPassword().equals(userPassword)) {
				return user;
			} else {
				throw new UserException("아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
			}
		}
	}

	@Override
	public void regist(User user) {
		dao.insert(user);
	}

	@Override
	public void update(User user) {
		dao.update(user);
	}

	@Override
	public void exit(String userId) {
		dao.delete(userId);
	}

}
