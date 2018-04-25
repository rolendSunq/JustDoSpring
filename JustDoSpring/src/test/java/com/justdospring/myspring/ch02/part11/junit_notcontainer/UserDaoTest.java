package com.justdospring.myspring.ch02.part11.junit_notcontainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.metadata.GenericTableMetaDataProvider;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-applicationContext.xml")
@DirtiesContext
public class UserDaoTest {
	@Autowired
	ApplicationContext context;
	
	private UserDao dao;
	
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() {
		dao = context.getBean("userDao", UserDao.class);
		
		user1 = new User("oldBoy", "박성용", "parkCastle", "oldBoyPark@naver.com");
		user2 = new User("neonKnight", "김기사", "nightKing", "neonKnight@naver.com");
		user3 = new User("paranoid", "이신경", "paranoid801", "paranoid801@naver.com");
	}
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), equalTo(0));
		
		dao.add(user1);
		dao.add(user2);
		dao.add(user3);
		
		
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
	
	@Test
	public void count() throws SQLException {
		assertThat(dao.getCount(), equalTo(3));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException{
		dao.get("unknownId");
	}
	
}
