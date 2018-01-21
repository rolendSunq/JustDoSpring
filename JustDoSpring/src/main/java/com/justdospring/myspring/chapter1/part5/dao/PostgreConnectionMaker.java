package com.justdospring.myspring.chapter1.part5.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/myTestGres", "postgres", "gre1234");
		return connection;
	}

}
