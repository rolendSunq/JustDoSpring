package com.justdospring.myspring.chapter3.part6.jdbccontext_separation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.justdospring.myspring.domain.User;


public class UserDao {
	@SuppressWarnings("unused")
	private DataSource dataSource;
	
	private JdbcContext jdbcContext;
	
	// JdbcContext를 DI 받도록 만든다.
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		
		jdbcContext.workWithStatementStrategy(new StatementStrategy() {
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
				PreparedStatement ps 	= connection.prepareStatement("select * from public_user where id = ?");
				ps.setString(1, paramUser.getId());
				
				try {
					resultSet = ps.executeQuery();
					if (resultSet.next()) {
						paramUser.setId(resultSet.getString("id"));
						paramUser.setName(resultSet.getString("name"));
						paramUser.setPassword(resultSet.getString("password"));
						paramUser.setEmail(resultSet.getString("email"));
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
				
				if (user == null) {
					throw new EmptyResultDataAccessException(1);
				}
				
				return ps;
			}
			
			public User getUser() {
				return user;
			}

		}
		
		StatementStrategy statementStrategy = new GetStatement(paramUser);
		jdbcContext.workWithStatementStrategy(statementStrategy);
		
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
		
		StatementStrategy statementStrategy = new DeleteAllStatement();
		jdbcContext.workWithStatementStrategy(statementStrategy);
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
		
		StatementStrategy statementStrategy = new GetCountStatement();
		jdbcContext.workWithStatementStrategy(statementStrategy);
		return ((GetCountStatement) statementStrategy).getCount();
	}
	
}
