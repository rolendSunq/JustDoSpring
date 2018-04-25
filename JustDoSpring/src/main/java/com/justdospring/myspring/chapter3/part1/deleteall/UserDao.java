package com.justdospring.myspring.chapter3.part1.deleteall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.justdospring.myspring.domain.User;


public class UserDao {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("insert into public_user(id, name, password, email, reg_date) values(?, ?, ?, ?, current_timestamp)");
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
		PreparedStatement ps = connection.prepareStatement("select * from public_user where id = ?");
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
		Connection connection = null;
		PreparedStatement ps  = null;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement("delete from public_user");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: 예외처리 방법이 아직 정의되지 않았다.
			throw e;
		} finally {
			// PreparedStatement close 처리 - PreparedStatement객체가 null일 경우 close할 필요가 없다.
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// empty --> close 메소드에 대해 예외 처리할 방법은 없다.
				}
			}
			
			// Connection close 처리 - Connection객체가 null일 경우 close할 필요가 없다.
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// empty --> close 메소드에 대해 예외 처리할 방법은 없다.
				}
			}
		}
		
		ps.close();
		connection.close();
	}
	
	public int getCount() throws SQLException{
		Connection connection = dataSource.getConnection();
		
		PreparedStatement ps = connection.prepareStatement("select count(*) from public_user");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		connection.close();
		
		return count;
	}
}
