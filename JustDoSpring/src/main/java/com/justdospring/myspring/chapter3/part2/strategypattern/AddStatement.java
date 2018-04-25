package com.justdospring.myspring.chapter3.part2.strategypattern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatement implements StatementStrategy {

	@Override
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
		return connection.prepareStatement("insert into public_user(id, name, password, email, reg_date) values(?, ?, ?, ?, current_timestamp)");
	}

}
