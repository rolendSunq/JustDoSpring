package com.justdospring.myspring.test;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.justdospring.myspring.chapter1.part10.dao.DaoFactory;
import com.justdospring.myspring.chapter1.part6.dao.UserDao;
/**
 * ApplicationContext를 적용하도록 하는 UserDao Test
 * @author developDec
 *
 */
public class UserDaoTestUsingApplicationContext {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
	}
}
