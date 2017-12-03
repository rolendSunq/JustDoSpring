package com.justdospring.myspring.chapter1.part8.dao;

import com.justdospring.myspring.chapter1.part5.dao.MySQLConnectionMaker;
import com.justdospring.myspring.chapter1.part6.dao.UserDao;

public class DaoFactory {
	public UserDao userDao() {
		return new UserDao(new MySQLConnectionMaker());
	}

	public AccountDao accountDao() {
		return new AccountDao(new MySQLConnectionMaker());
	}

	public MessageDao messageDao() {
		return new MessageDao(new MySQLConnectionMaker());
	}
}
