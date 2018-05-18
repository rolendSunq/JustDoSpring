package com.justdospring.myspring.chapter3.part6.jdbccontext_separation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	private DataSource dataSource;

	// DataSource type bean을 DI 받을 수 있게 준비해둔다.
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void workWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException{
		Connection connection 				= null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = statementStrategy.makePreparedStatement(connection);
		} catch(SQLException sqle) {
			throw sqle;
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// empty
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// empty
				}
			}
		}
	}
}
