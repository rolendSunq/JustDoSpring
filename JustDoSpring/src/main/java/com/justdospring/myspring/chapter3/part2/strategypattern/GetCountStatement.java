package com.justdospring.myspring.chapter3.part2.strategypattern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetCountStatement implements StatementStrategy {

	@Override
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
		return connection.prepareStatement("select count(*) from public_user");
	}

}
