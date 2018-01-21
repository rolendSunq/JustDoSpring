package com.justdospring.myspring.chapter1.part11.singltondao;

import com.justdospring.myspring.chapter1.part5.dao.ConnectionMaker;

public class UserDao {
	private static UserDao INSTANCE;
	private static ConnectionMaker connectionMaker;

	private UserDao(ConnectionMaker connectionMaker) {
		UserDao.connectionMaker = connectionMaker;
	}

	public static synchronized UserDao getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UserDao(connectionMaker);
		}

		return INSTANCE;
	}
}
