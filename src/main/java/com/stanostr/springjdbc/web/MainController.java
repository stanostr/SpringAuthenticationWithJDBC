package com.stanostr.springjdbc.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stanostr.springjdbc.data.User;
import com.stanostr.springjdbc.data.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/main")
public class MainController {
	private UserRepository repo;
	
	public MainController(UserRepository userRepository) {
		repo = userRepository;
	}
	
	@GetMapping
	public String displayWelcome(Model model, Principal principal)
	{
		User user = repo.findByUsername(principal.getName());

		model.addAttribute("firstname", user.getFirstName());
		log.info("Users first name=" + user.getFirstName());
		return "main";
	}

}
