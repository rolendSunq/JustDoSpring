package com.justdospring.myspring.chapter1.part5.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
