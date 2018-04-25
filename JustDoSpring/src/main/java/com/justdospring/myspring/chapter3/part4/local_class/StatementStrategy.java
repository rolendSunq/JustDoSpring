package com.justdospring.myspring.chapter3.part4.local_class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException;
}
