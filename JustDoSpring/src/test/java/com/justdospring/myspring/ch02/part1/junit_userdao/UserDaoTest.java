package com.justdospring.myspring.ch02.part1.junit_userdao;

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
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		User user = new User();
		
		user.setId("paranoid");
		user.setName("이현식");
		user.setPassword("paranoid2131");
		user.setEmail("outParanoid@naver.com");
		dao.add(user);
		
		User findUser = dao.get(user.getId());
		
		// is 메소드는 deprecated 되었으며 equalTo 메소드로 사용한다.
		assertThat(findUser.getName(), equalTo(user.getName()));
		assertThat(findUser.getPassword(), equalTo(user.getPassword()));
	}
}
