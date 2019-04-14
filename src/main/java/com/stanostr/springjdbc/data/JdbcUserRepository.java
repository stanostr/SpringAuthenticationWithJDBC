package com.stanostr.springjdbc.data;

import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
		jdbc = jdbcTemplate;
	}
	
	@Override
	public User save(User user) {
		jdbc.update("insert into Users(username, password, enabled) values (?, ?, ?)", user.getUsername(), user.getPassword(), 1);
		jdbc.update("insert into UserAuthorities(username, authority) values (?, ?)", user.getUsername(), "USER");
		return user;
	}

}
