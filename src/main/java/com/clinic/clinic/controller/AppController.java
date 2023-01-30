package com.clinic.clinic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.clinic.clinic.entity.User;
import com.clinic.clinic.service.UserService;

/**
 * This is the Main Application controller for registering new user and user
 * activities and login page for the admin and user
 * 
 * @author priyanshu.goyal
 *
 */
@Controller
public class AppController {

	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	UserService userService;

	@GetMapping("")
	public String getHomePage() {
		logger.info("getting home page");
		return "index";
	}

	@GetMapping("/index")
	public String getIndex() {
		logger.info("getting home page");
		return "index";
	}

	@GetMapping("/clinic")
	public String clinic() {
		logger.info("getting clinic home page");
		return "clinic";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		logger.info("getting signup_form");
		model.addAttribute("user", new User());
		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		userService.saveUserWithDefaultRole(user);
		logger.info("registration of user is successful " + user.getUsername());
		return "register_success";
	}

	@GetMapping("/name")
	public String nameEdit(Model model) {
		model.addAttribute("user", new User());
		logger.info("editing the name for user");
		return "name-edit";
	}

	@PostMapping("/edit_name")
	public String nameUpdate(User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails detail = (UserDetails) auth.getPrincipal();
		String username = detail.getUsername();
		User userFromDb = userService.findByUsername(username);
		userFromDb.setFullname(user.getUsername());
		userService.saveUser(userFromDb);
		logger.info("Successfully updated the user " + userFromDb.getUsername());
		return "clinic";
	}
}
