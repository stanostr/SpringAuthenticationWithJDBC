package com.stanostr.springjdbc.web;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stanostr.springjdbc.data.User;
import com.stanostr.springjdbc.data.UserRepository;
import com.stanostr.springjdbc.security.RegistrationForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {
	private UserRepository repo;
	
	public RegistrationController(UserRepository userRepository) {
		repo = userRepository;
	}
	
	@GetMapping
	public String registerForm(Model model)
	{
		model.addAttribute("registrationForm", new RegistrationForm());
		return "register";
	}
	
	@PostMapping
	public String processRegistration(@Valid RegistrationForm registrationForm, Errors errors, ModelMap model)
	{
		if(errors.hasErrors())
		{
			log.info("Errors found: " + errors.getErrorCount());
			return "register";
		}
		User user = registrationForm.toUser(new BCryptPasswordEncoder());
		if(repo.userExists(user))
		{
			model.addAttribute("userexists", "This username is already taken!");
			log.info("User already exists");
			return "register";
		}
		repo.save(user);
		return "redirect:/login";
	}
}
