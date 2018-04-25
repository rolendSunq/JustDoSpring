package com.justdospring.myspring.chapter3.part3.method_isolation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy {

	@Override
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("delete from public_user");
		ps.executeUpdate();
		return ps;
	}

}
