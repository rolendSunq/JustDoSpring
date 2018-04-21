package com.justdospring.myspring.ch02.part3.junit_addmethod;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.justdospring.myspring.chapter1.domain.User;

public class UserDaoTest {
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext2.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), equalTo(0));
		
		User user = new User();
		
		user.setId("childrenOfGrave");
		user.setName("강석돌");
		user.setPassword("cogKang1234");
		user.setEmail("cogKang12@naver.com");
		dao.add(user);
		
		User findUser = dao.get(user.getId());
		
		// is 메소드는 deprecated 되었으며 equalTo 메소드로 사용한다.
		assertThat(findUser.getName(), equalTo(user.getName()));
		assertThat(findUser.getPassword(), equalTo(user.getPassword()));
	}
	
}
