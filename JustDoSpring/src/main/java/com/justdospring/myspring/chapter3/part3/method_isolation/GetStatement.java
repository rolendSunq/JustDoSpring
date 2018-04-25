package com.justdospring.myspring.chapter3.part3.method_isolation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.justdospring.myspring.domain.User;

public class GetStatement implements StatementStrategy {
	private User user;
	private User resultUser;
	
	public GetStatement(User user) {
		this.user = user;
	}

	@Override
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("select * from public_user where id = ?");
		ps.setString(1, user.getId());

		ResultSet resultSet = ps.executeQuery();
		
		User resultUser = null;
		if (resultSet.next()) {
			resultUser = new User();
			resultUser.setId(resultSet.getString("id"));
			resultUser.setName(resultSet.getString("name"));
			resultUser.setPassword(resultSet.getString("password"));
			resultUser.setEmail(resultSet.getString("email"));
		}
		
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// empty
			}
		}
		
		if (resultUser == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return ps;
	}
	
	public User getUser() {
		return resultUser;
	}

}
