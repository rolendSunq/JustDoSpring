package com.justdospring.myspring.ch02.part6.junit_exception;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;


public class UserDaoTest {
	@Test(expected=EmptyResultDataAccessException.class)
	public void addAndGet() throws SQLException, ClassNotFoundException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext5.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), equalTo(0));
		dao.get("unknownId");
		
		User user1 = new User("oldBoy", "박성용", "parkCastle", "oldBoyPark@naver.com");
		User user2 = new User("neonKnight", "김기사", "nightKing", "neonKnight@naver.com");
		User user3 = new User("paranoid", "이신경", "paranoid801", "paranoid801@naver.com");
		
		dao.add(user1);
		dao.add(user2);
		dao.add(user3);
		
		assertThat(dao.getCount(), equalTo(3));
		
		User userGet1 = dao.get(user1.getId());
		assertThat(userGet1.getName(), equalTo(user1.getName()));
		assertThat(userGet1.getPassword(), equalTo(user1.getPassword()));
		
		User userGet2 = dao.get(user2.getId());
		assertThat(userGet2.getName(), equalTo(user2.getName()));
		assertThat(userGet2.getPassword(), equalTo(user2.getPassword()));
		
		User userGet3 = dao.get(user3.getId());
		assertThat(userGet3.getName(), equalTo(user3.getName()));
		assertThat(userGet3.getPassword(), equalTo(user3.getPassword()));
	}
	
}
