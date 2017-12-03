package com.justdospring.myspring.test;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.justdospring.myspring.chapter1.domain.User;
import com.justdospring.myspring.chapter1.part10.dao.DaoFactory;
import com.justdospring.myspring.chapter1.part6.dao.UserDao;

public class UserDaoTest6 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("pinkFloyd");
		user.setName("핑크플로이드");
		user.setPassword("time");

		dao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId() + " 조회 성공");
	}

}
