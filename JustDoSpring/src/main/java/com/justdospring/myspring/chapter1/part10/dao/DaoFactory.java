package com.justdospring.myspring.chapter1.part10.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.justdospring.myspring.chapter1.part5.dao.ConnectionMaker;
import com.justdospring.myspring.chapter1.part5.dao.MySQLConnectionMaker;
import com.justdospring.myspring.chapter1.part5.dao.OracleConnectionMaker;
import com.justdospring.myspring.chapter1.part6.dao.UserDao;

@Configuration
public class DaoFactory {
	@Bean
	public UserDao userDao() {
		return new UserDao(getConnectionMaker("MYSQL"));
	}

	@Bean
	public ConnectionMaker getConnectionMaker(String vender) {
		ConnectionMaker connectionMaker = null;

		if (vender.equals("MYSQL")) {
			connectionMaker = new MySQLConnectionMaker();
		} else if (vender.equals("ORACLE")) {
			connectionMaker = new OracleConnectionMaker();
		}

		return connectionMaker;
	}
}
