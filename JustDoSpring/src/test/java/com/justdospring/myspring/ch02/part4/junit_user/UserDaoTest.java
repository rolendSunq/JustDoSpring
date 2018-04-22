package com.justdospring.myspring.ch02.part4.junit_user;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class UserDaoTest {
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext3.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), equalTo(0));
		
		User user1 = new User("oldBoy", "박성용", "parkCastle", "oldBoyPark@naver.com");
		User user2 = new User("neonKnight", "김기사", "nightKing", "neonKnight@naver.com");
		User user3 = new User("paranoid", "이신경", "paranoid801", "paranoid801@naver.com");
		
		dao.add(user1);
		assertThat(dao.getCount(), equalTo(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), equalTo(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), equalTo(3));
	}
	
}
