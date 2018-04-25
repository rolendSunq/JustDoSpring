package com.justdospring.myspring.chapter3.part4.local_class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.justdospring.myspring.domain.User;

public class GetStatement implements StatementStrategy {
	private User user;
	
	public GetStatement(User user) {
		this.user = user;
	}

	@Override
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
		ResultSet resultSet 	= null;
		User tmpUser 			= null;
		PreparedStatement ps 	= connection.prepareStatement("select * from public_user where id = ?");
		ps.setString(1, user.getId());
		try {
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				tmpUser = new User();
				tmpUser.setId(resultSet.getString("id"));
				tmpUser.setName(resultSet.getString("name"));
				tmpUser.setPassword(resultSet.getString("password"));
				tmpUser.setEmail(resultSet.getString("email"));
				user = tmpUser;
			}
		} catch(SQLException e) {
			throw e;
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// empty
				}
			}
			
		}
		
		if (tmpUser == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return ps;
	}
	
	public User getUser() {
		return user;
	}

}
