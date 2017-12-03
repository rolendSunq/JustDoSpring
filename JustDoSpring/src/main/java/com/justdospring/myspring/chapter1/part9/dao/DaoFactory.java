package com.justdospring.myspring.chapter1.part9.dao;

import com.justdospring.myspring.chapter1.part5.dao.ConnectionMaker;
import com.justdospring.myspring.chapter1.part5.dao.MySQLConnectionMaker;
import com.justdospring.myspring.chapter1.part5.dao.OracleConnectionMaker;
import com.justdospring.myspring.chapter1.part6.dao.UserDao;
import com.justdospring.myspring.chapter1.part8.dao.AccountDao;
import com.justdospring.myspring.chapter1.part8.dao.MessageDao;

public class DaoFactory {
	public UserDao userDao() {
		return new UserDao(connectionMaker("MYSQL"));
	}

	public AccountDao accountDao() {
		return new AccountDao(connectionMaker("MYSQL"));
	}

	public MessageDao messageDao() {
		return new MessageDao(connectionMaker("MYSQL"));
	}

	public ConnectionMaker connectionMaker(String vender) {
		ConnectionMaker connectionMaker = null;

		if (vender.equals("MYSQL")) {
			connectionMaker = new MySQLConnectionMaker();
		} else if (vender.equals("ORACLE")) {
			connectionMaker = new OracleConnectionMaker();
		}

		return connectionMaker;
	}
}
