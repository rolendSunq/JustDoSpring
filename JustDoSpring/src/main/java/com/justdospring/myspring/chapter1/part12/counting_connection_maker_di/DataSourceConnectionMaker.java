package com.justdospring.myspring.chapter1.part12.counting_connection_maker_di;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/devgres", "postgres", "gre1234");
		return connection;
	}

}
