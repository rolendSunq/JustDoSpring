package com.justdospring.myspring.chapter1.part13.datasource_convert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/devgres", "postgres", "dev1234");
		return connection;
	}

}
