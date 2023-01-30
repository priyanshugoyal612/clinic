package com.clinic.clinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinic.clinic.entity.Role;
import com.clinic.clinic.entity.User;
import com.clinic.clinic.repository.RoleRepository;
import com.clinic.clinic.repository.UserRepository;

/**
 * This is the main user service contains all the business logic to fetch data
 * from data base and update and delete in the database
 * 
 * @author priyanshu.goyal
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	/**
	 * This method find the user on the basis of id from the database
	 * 
	 * @param id
	 * @return User
	 */
	public User findById(Long id) {
		return userRepository.findUserById(id);
	}

	/**
	 * This method find the user on the basis of username from the database
	 * 
	 * @param username
	 * @return User
	 */
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * This method find the all the existing users from the database
	 * 
	 * @return List<User>
	 */
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * This method save the user data with default role as user from the database
	 * 
	 * @param user
	 */
	public void saveUserWithDefaultRole(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Role roleUser = roleRepository.findByName("USER");
		user.addRole(roleUser);
		userRepository.save(user);
	}

	/**
	 * This method returns the all the roles from the database
	 * 
	 * @return List<Role>
	 */
	public List<Role> listRoles() {
		return roleRepository.findAll();
	}

	/**
	 * This method update the user data in the database
	 * 
	 * @param user
	 */
	public void updateUser(User user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}

	/**
	 * This method save the user in the database
	 * 
	 * @param userFromDb
	 */
	public void saveUser(User user) {
		userRepository.save(user);
	}
}