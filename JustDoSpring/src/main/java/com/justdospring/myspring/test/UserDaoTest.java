package com.justdospring.myspring.test;

import java.sql.SQLException;

import com.justdospring.myspring.chapter1.dao.UserDao;
import com.justdospring.myspring.chapter1.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao userDao = new UserDao();
		User user = new User();
		user.setId("whiteShip");
		user.setName("선큐");
		user.setPassword("hellospring");

		userDao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = userDao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId() + " 조회 성공");
	}

}
