package com.justdospring.myspring.ch03.part6.junit_jdbccontext_seperation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.justdospring.myspring.chapter3.part6.jdbccontext_separation.UserDao;
import com.justdospring.myspring.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext10.xml")
@DirtiesContext
public class UserDaoTest {
	@Autowired
	private ApplicationContext context;
	
	private UserDao userDao;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() {
		userDao = context.getBean("userDao", UserDao.class);
		user1 = new User("oldBoy", "홍길동", "parkCastle", "oldBoyPark@naver.com");
		user2 = new User("neonKnight", "강감찬", "nightKing", "neonKnight@naver.com");
		user3 = new User("paranoid", "을지문덕", "paranoid801", "paranoid801@naver.com");
	}
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		
		userDao.deleteAll();
		assertThat(userDao.getCount(), equalTo(0));
		
		userDao.add(user1);
		userDao.add(user2);
		userDao.add(user3);
		
		
		User userGet1 = userDao.get(user1.getId());
		assertThat(userGet1.getName(), equalTo(user1.getName()));
		assertThat(userGet1.getPassword(), equalTo(user1.getPassword()));
		
		User userGet2 = userDao.get(user2.getId());
		assertThat(userGet2.getName(), equalTo(user2.getName()));
		assertThat(userGet2.getPassword(), equalTo(user2.getPassword()));
		
		User userGet3 = userDao.get(user3.getId());
		assertThat(userGet3.getName(), equalTo(user3.getName()));
		assertThat(userGet3.getPassword(), equalTo(user3.getPassword()));
	}
	
	@Test
	public void count() throws SQLException {
		assertThat(userDao.getCount(), equalTo(3));
	}
	
	/*
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException{
		userDao.get("unknownId");
	}
	*/
	
}
