package com.justdospring.myspring.chapter3.part3.method_isolation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException;
}
