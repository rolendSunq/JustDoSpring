package com.justdospring.myspring.chapter3.part5.anonymous_inner_class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.justdospring.myspring.domain.User;


public class UserDao {
	private DataSource dataSource;
	private JdbcContext jdbcContext;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		
		jdbcContextWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("insert into public_user(id, name, password, email, reg_date) values(?, ?, ?, ?, current_timestamp)");
				
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getEmail());
				
				return ps;
			}
		});
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		User paramUser = new User();
		paramUser.setId(id);
		
		class GetStatement implements StatementStrategy {
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
		
		statementStrategy = new GetStatement(paramUser);
		jdbcContextWithStatementStrategy(statementStrategy);
		
		return ((GetStatement) statementStrategy).getUser();
	}
	
	public void deleteAll() throws SQLException {
		
		class DeleteAllStatement implements StatementStrategy {

			@Override
			public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("delete from public_user");
				ps.executeUpdate();
				return ps;
			}

		}
		
		statementStrategy = new DeleteAllStatement();
		jdbcContextWithStatementStrategy(statementStrategy);
	}
	
	public int getCount() throws SQLException{
		
		class GetCountStatement implements StatementStrategy {
			private int count;

			@Override
			public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
				ResultSet rs 			= null;
				PreparedStatement ps 	= connection.prepareStatement("select count(*) from public_user");
				
				try {
					rs = ps.executeQuery();
					rs.next();
					count = rs.getInt(1);
				} catch (SQLException e) {
					throw e;
				} finally {
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e) {
							// empty
						}
					}
				}
				
				return ps;
			}

			public int getCount() {
				return count;
			}
		}
		
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
