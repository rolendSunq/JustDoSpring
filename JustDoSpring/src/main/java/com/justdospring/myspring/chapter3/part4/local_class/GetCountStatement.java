package com.justdospring.myspring.chapter3.part4.local_class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCountStatement implements StatementStrategy {
	private int count;

	@Override
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
		ResultSet rs 			= null;
		PreparedStatement ps 	= connection.prepareStatement("select count(*) from public_user");
		
		try {
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// empty
				}
			}
		}
		
		return ps;
	}

	public int getCount() {
		return count;
	}
}
