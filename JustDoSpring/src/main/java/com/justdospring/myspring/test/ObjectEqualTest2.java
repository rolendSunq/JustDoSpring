package com.justdospring.myspring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.justdospring.myspring.chapter1.part10.dao.DaoFactory;
import com.justdospring.myspring.chapter1.part6.dao.UserDao;

public class ObjectEqualTest2 {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao1 = context.getBean("userDao", UserDao.class);
		UserDao dao2 = context.getBean("userDao", UserDao.class);

		System.out.println(dao1);
		System.out.println(dao2);
	}
}
