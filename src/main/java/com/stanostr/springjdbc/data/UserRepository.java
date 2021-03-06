package com.stanostr.springjdbc.data;

public interface UserRepository {
	User save(User user);
	boolean userExists(User user);
	User findByUsername(String username);
}
