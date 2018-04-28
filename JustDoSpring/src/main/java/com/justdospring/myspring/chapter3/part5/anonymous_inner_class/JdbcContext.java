package com.justdospring.myspring.chapter3.part5.anonymous_inner_class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void workWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException{
		Connection connection 				= null;
		PreparedStatement preparedSatement 	= null;
		
		try {
			connection 			= dataSource.getConnection();
			preparedSatement 	= statementStrategy.makePreparedStatement(connection);
		} catch(SQLException e) {
			throw e;
		} finally {
			if (preparedSatement != null) {
				try {
					preparedSatement.close();
				} catch(SQLException e) {
					// empty
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch(SQLException e) {
					// empty
				}
			}
		}
	}
}
