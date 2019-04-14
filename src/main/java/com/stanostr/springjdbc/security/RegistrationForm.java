package com.stanostr.springjdbc.security;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.stanostr.springjdbc.data.User;

import lombok.Data;

@Data
public class RegistrationForm {
	@NotBlank(message="Field cannot be left blank")
	private String firstname;
	@NotBlank(message="Field cannot be left blank")
	private String lastname;
	@Size(min=3, max=20, message="Username must be between 3 and 20 characters")
	private String username;
	@Size(min=6, max=20, message="Password must be between 6 and 20 characters in length")
	private String password;
	@Size(min=6, max=20, message="Password must be between 6 and 20 characters in length")
	private String confirm;
	
	@AssertTrue(message="Passwords do not match")
	public boolean getPassMatch()
	{
		return confirm.equals(password);
	}
	
	public User toUser(PasswordEncoder passwordEncoder)
	{
		return new User(firstname, lastname, username, passwordEncoder.encode(password));
	}
}
