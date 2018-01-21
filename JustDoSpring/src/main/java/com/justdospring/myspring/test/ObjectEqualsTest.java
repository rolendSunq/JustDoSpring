package com.justdospring.myspring.test;

import com.justdospring.myspring.chapter1.part10.dao.DaoFactory;
import com.justdospring.myspring.chapter1.part6.dao.UserDao;

public class ObjectEqualsTest {
	public static void main(String[] args) {
		DaoFactory factory = new DaoFactory();
		UserDao dao1 = factory.userDao();
		UserDao dao2 = factory.userDao();

		System.out.println(dao1);
		System.out.println(dao2);
	}
}
