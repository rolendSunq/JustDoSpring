package com.justdospring.myspring.chapter3.part3.method_isolation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.justdospring.myspring.domain.User;


public class UserDao {
	private DataSource dataSource;
	private StatementStrategy statementStrategy;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		statementStrategy = new AddStatement(user);
		jdbcContextWithStatementStrategy(statementStrategy);
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		User paramUser = new User();
		paramUser.setId(id);
		statementStrategy = new GetStatement(paramUser);
		jdbcContextWithStatementStrategy(statementStrategy);

		return ((GetStatement) statementStrategy).getUser();
	}
	
	public void deleteAll() throws SQLException {
		statementStrategy = new DeleteAllStatement();
		jdbcContextWithStatementStrategy(statementStrategy);
	}
	
	public int getCount() throws SQLException{
		statementStrategy = new GetCountStatement();
		jdbcContextWithStatementStrategy(statementStrategy);
		return ((GetCountStatement) statementStrategy).getCount();
	}
	
	private void jdbcContextWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException{
		Connection connection 	= null;
		PreparedStatement ps	= null;
		
		try {
			connection 	= dataSource.getConnection();
			ps			= statementStrategy.makePreparedStatement(connection);
		} catch (SQLException e) {
			throw e;
		} finally {
			// PreparedStatement close
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// empty --> close 예외설정 미정의
				}
			}
			
			// Connection close
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// empty --> close 예외설정 미정의
				}
			}
		}
	}
}
