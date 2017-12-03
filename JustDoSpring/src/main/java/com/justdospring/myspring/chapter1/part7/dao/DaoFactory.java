package com.justdospring.myspring.chapter1.part7.dao;

import com.justdospring.myspring.chapter1.part5.dao.MySQLConnectionMaker;
import com.justdospring.myspring.chapter1.part5.dao.OracleConnectionMaker;
import com.justdospring.myspring.chapter1.part6.dao.UserDao;

public class DaoFactory {
	public UserDao getMySQLUserDao() {
		return new UserDao(new MySQLConnectionMaker());
	}

	public UserDao getOracleUserDao() {
		return new UserDao(new OracleConnectionMaker());
	}
}
