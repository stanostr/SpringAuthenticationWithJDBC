package com.stanostr.springjdbc.data;

import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.stanostr.springjdbc.web.MainController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class JdbcUserRepository implements UserRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
		jdbc = jdbcTemplate;
	}
	
	@Override
	public User save(User user) {
		jdbc.update("insert into Users(username, password, enabled, firstname, lastname) values (?, ?, ?, ?, ?)", user.getUsername(), user.getPassword(), 1, user.getFirstName(), user.getLastName());
		jdbc.update("insert into UserAuthorities(username, authority) values (?, ?)", user.getUsername(), "USER");
		return user;
	}
	
	@Override
	public boolean userExists(User user)
	{
		int total =  jdbc.queryForObject("select count(*) from Users where username = ?", Integer.class, user.getUsername());
		return total != 0;
	}

	@Override
	public User findByUsername(String username) {
		User user = jdbc.queryForObject("select firstname, lastname, username from Users where username=?", this::mapRowToUser, username);
		log.info("Users first name=" + user.getFirstName());
		return user;
	}
	
	private User mapRowToUser(ResultSet resultSet, int row) throws SQLException
	{
		User user = new User(resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("username"), null);
		
		return user;
	}

}
