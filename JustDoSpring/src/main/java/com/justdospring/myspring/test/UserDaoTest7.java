package com.justdospring.myspring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.justdospring.myspring.chapter1.part10.dao.DaoFactory;
import com.justdospring.myspring.chapter1.part6.dao.UserDao;

public class UserDaoTest7 {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		@SuppressWarnings("unused")
		UserDao dao = context.getBean("userDao", UserDao.class);
	}
}
