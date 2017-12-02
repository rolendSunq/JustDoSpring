package com.justdospring.myspring.test;

import java.sql.SQLException;

import com.justdospring.myspring.chapter1.domain.User;
import com.justdospring.myspring.chapter1.part4.dao.UserDao;

public class UserDaoTest2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao userDao = new UserDao();
		User user = new User();
		user.setId("blackSabbath");
		user.setName("블랙사바스");
		user.setPassword("ironMan");

		userDao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = userDao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId() + " 조회 성공");
	}

}
