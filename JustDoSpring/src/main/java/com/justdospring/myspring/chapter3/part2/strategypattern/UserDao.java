package com.justdospring.myspring.chapter3.part2.strategypattern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.justdospring.myspring.domain.User;


public class UserDao {
	private DataSource dataSource;
	private StatementStrategy statementStrategy;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection connection = dataSource.getConnection();
		statementStrategy = new AddStatement();
		PreparedStatement ps = statementStrategy.makePreparedStatement(connection);
		
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
		statementStrategy = new GetStatement();
		PreparedStatement ps = statementStrategy.makePreparedStatement(connection);
		
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
		statementStrategy = new DeleteAllStatement();
		
		try {
			connection = dataSource.getConnection();
			ps = statementStrategy.makePreparedStatement(connection);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: ����ó�� ����� ���� ���ǵ��� �ʾҴ�.
			throw e;
		} finally {
			// PreparedStatement close ó�� - PreparedStatement��ü�� null�� ��� close�� �ʿ䰡 ����.
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// empty --> close �޼ҵ忡 ���� ���� ó���� ����� ����.
				}
			}
			
			// Connection close ó�� - Connection��ü�� null�� ��� close�� �ʿ䰡 ����.
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// empty --> close �޼ҵ忡 ���� ���� ó���� ����� ����.
				}
			}
		}
		
		ps.close();
		connection.close();
	}
	
	public int getCount() throws SQLException{
		Connection connection 	= null;
		PreparedStatement ps 	= null;
		ResultSet rs 			= null;
		statementStrategy 		= new GetCountStatement();
		int count				= 0;
		
		try {
			connection 	= dataSource.getConnection();
			ps 			= statementStrategy.makePreparedStatement(connection);
			rs 			= ps.executeQuery();
			
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			// TODO: handle exception
			throw e;
		} finally {
			// Connection close
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO: ����ó�� ������
					// empty
				}
			}
			
			// PreparedStatement close
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO: ����ó�� ������
					// empty
				}
			}
			
			// ResultSet close
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO: ����ó�� ������
					// empty
				}
			}
			
		}
		
		return count;
	}
}
