package com.justdospring.myspring.chapter1.part12.counting_connection_maker_di;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
