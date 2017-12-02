package com.justdospring.myspring.test;

import java.sql.SQLException;

import com.justdospring.myspring.chapter1.domain.User;
import com.justdospring.myspring.chapter1.part5.dao.MySQLConnectionMaker;
import com.justdospring.myspring.chapter1.part6.dao.UserDao;

public class UserDaoTest4 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao userDao = new UserDao(new MySQLConnectionMaker());
		User user = new User();
		user.setId("theWho");
		user.setName("더후");
		user.setPassword("BabaORiley");

		userDao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = userDao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId() + " 조회 성공");
	}
}
