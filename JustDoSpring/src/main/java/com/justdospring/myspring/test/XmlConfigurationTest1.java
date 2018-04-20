package com.justdospring.myspring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.justdospring.myspring.chapter1.part12.counting_connection_maker_di.UserDao;


public class XmlConfigurationTest1 {

	public static void main(String[] args) {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
	}

}
