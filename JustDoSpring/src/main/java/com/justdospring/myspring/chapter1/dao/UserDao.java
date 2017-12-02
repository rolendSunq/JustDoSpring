package com.justdospring.myspring.chapter1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.justdospring.myspring.chapter1.domain.User;

public class UserDao {
	public void add(User user) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/springexam", "root", "dev1234");

		PreparedStatement ps = connection.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		connection.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/springexam", "root", "dev1234");

		PreparedStatement ps = connection.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet resultSet = ps.executeQuery();
		resultSet.next();
		User user = new User();
		user.setId(resultSet.getString("id"));
		user.setName(resultSet.getString("name"));
		user.setPassword(resultSet.getString("password"));

		resultSet.close();
		ps.close();
		connection.close();

		return user;
	}
}
