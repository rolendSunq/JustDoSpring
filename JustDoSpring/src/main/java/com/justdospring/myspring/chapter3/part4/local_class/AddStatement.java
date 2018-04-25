package com.justdospring.myspring.chapter3.part4.local_class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.justdospring.myspring.domain.User;

public class AddStatement implements StatementStrategy {
	private User user;
	
	public AddStatement(User user) {
		this.user = user;
	}
	
	@Override
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("insert into public_user(id, name, password, email, reg_date) values(?, ?, ?, ?, current_timestamp)");
		
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getEmail());
		
		ps.executeUpdate();
		
		return ps;
	}

}
