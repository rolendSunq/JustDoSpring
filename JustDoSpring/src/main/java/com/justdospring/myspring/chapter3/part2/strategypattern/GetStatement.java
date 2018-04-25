package com.justdospring.myspring.chapter3.part2.strategypattern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetStatement implements StatementStrategy {

	@Override
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
		return connection.prepareStatement("select * from public_user where id = ?");
	}

}
