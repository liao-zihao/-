package com.lzh.dao;

import com.lzh.pojo.User;

public class UserDaoTest {
	public static void main(String[] args) {
		UserDao userDao=new UserDaoImpl();
		User user = new User();
		user.setUsername("ccc");
		user.setPassword("ccc");
		user = userDao.login(user);
		System.out.println(user.getId()+user.getType());
	}
	
}
