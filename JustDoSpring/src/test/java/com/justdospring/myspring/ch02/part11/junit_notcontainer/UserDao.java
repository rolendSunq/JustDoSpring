package com.justdospring.myspring.ch02.part11.junit_notcontainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;


public class UserDao {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("insert into users(id, name, password, email, reg_date) values(?, ?, ?, ?, current_timestamp)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		ps.setString(4,  user.getEmail());

		ps.executeUpdate();

		ps.close();
		connection.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet resultSet = ps.executeQuery();
		User user = null;
		if (resultSet.next()) {
			user = new User();
			user.setId(resultSet.getString("id"));
			user.setName(resultSet.getString("name"));
			user.setPassword(resultSet.getString("password"));
			user.setEmail(resultSet.getString("email"));
		}

		resultSet.close();
		ps.close();
		connection.close();
		
		if (user == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return user;
	}
	
	public void deleteAll() throws SQLException {
		Connection connection = dataSource.getConnection();
		
		PreparedStatement ps = connection.prepareStatement("delete from users");
		
		ps.executeUpdate();
		
		ps.close();
		connection.close();
	}
	
	public int getCount() throws SQLException{
		Connection connection = dataSource.getConnection();
		
		PreparedStatement ps = connection.prepareStatement("select count(*) from users");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		connection.close();
		
		return count;
	}
}
