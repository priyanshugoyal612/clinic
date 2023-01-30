package com.clinic.clinic.config;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.clinic.clinic.entity.Role;
import com.clinic.clinic.entity.User;
import com.clinic.clinic.repository.UserRepository;
import com.clinic.clinic.service.UserService;

import jakarta.annotation.PostConstruct;

/**
 * @author priyanshu.goyal
 *
 */
@Component
public class DBInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DBInitializer.class);

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserService userService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	/**
	 * This method is initialized the database with default admin and a user
	 */
	@PostConstruct
	public void userCreationAdmin() {
		logger.info("Inserting admin data in database");
		User admin = adminCreation("admin", "admin", "admin");
		userService.saveUser(admin);
		logger.info("Inserting User data in database");
		User normal = userCreation("normal", "normal", "normal");
		userRepo.save(normal);
	}

	private User adminCreation(String username, String password, String fullname) {
		Role role = new Role();
		role.setName("ADMIN");
		User admin = new User(username, passwordEncoder.encode(password), fullname, Set.of(role));
		return admin;
	}

	private User userCreation(String username, String password, String fullname) {
		Role role = new Role();
		role.setName("USER");
		User user = new User(username, passwordEncoder.encode(password), fullname, Set.of(role));
		return user;
	}

}
