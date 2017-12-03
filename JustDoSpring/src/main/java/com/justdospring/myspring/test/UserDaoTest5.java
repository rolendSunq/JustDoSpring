package com.justdospring.myspring.test;

import java.sql.SQLException;

import com.justdospring.myspring.chapter1.domain.User;
import com.justdospring.myspring.chapter1.part6.dao.UserDao;
import com.justdospring.myspring.chapter1.part7.dao.DaoFactory;

public class UserDaoTest5 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao userDao = new DaoFactory().getMySQLUserDao();
		User user = new User();
		user.setId("cream");
		user.setName("크림");
		user.setPassword("SunshineOfYourLove");

		userDao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = userDao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId() + " 조회 성공");
	}
}
