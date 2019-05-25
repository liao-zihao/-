package com.lzh.service;

import com.lzh.dao.UserDao;
import com.lzh.dao.UserDaoImpl;
import com.lzh.pojo.User;

public class UserService {
	public User login(User user) {
		UserDao dao = new UserDaoImpl();
		return dao.login(user);
	}
}
