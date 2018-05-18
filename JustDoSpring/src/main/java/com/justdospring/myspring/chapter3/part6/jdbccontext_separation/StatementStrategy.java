package com.justdospring.myspring.chapter3.part6.jdbccontext_separation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException;
}
