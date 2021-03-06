package com.justdospring.myspring.chapter3.part5.anonymous_inner_class;

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
	
	// JdbcContext 클래스 안으로 이동하였으므로 메소드 명을 변경했다.
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
