package com.clinic.clinic.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clinic.clinic.entity.Pet;
import com.clinic.clinic.entity.Role;
import com.clinic.clinic.entity.User;
import com.clinic.clinic.entity.enums.PetType;
import com.clinic.clinic.service.PetService;
import com.clinic.clinic.service.UserService;

/**
 * This is the Main controller for admin related functionality which uses user
 * service and pet service to manipulate the data
 * 
 * @author priyanshu.goyal
 *
 */
@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	public PetService petService;

	@Autowired
	public UserService userService;

	@GetMapping("/admin")
	public String adminHome() {
		logger.info("getting admin home page");
		return "admin";
	}

	@GetMapping("/admin/pets")
	public String listAllPets(Model model) {
		List<Pet> listPets = petService.findAllPets();
		model.addAttribute("listPets", listPets);
		logger.info("returning pets");
		return "pets";
	}

	@GetMapping("/admin/statistic")
	public String getStatistic(Model model) {
		List<Pet> listPets = petService.findAllPets();
		Map<PetType, List<Pet>> result = listPets.stream().collect(Collectors.groupingBy(Pet::getType));
		result.forEach((k, v) -> {
			model.addAttribute(k.name(), v.size());
		});
		logger.info("getting statistic data");
		return "statistic";
	}

	@GetMapping("/admin/users")
	public String listUsers(Model model) {
		List<User> listUsers = userService.findAllUsers();
		model.addAttribute("listUsers", listUsers);
		logger.info("getting users data");
		return "users";
	}

	@GetMapping(path = "/admin/users/edit/{id}")
	public ModelAndView showEditFormUser(@PathVariable(name = "id") Long id) {
		ModelAndView editViewUser = new ModelAndView("edit-user");
		User user = userService.findById(id);
		List<Role> listRoles = userService.listRoles();
		editViewUser.addObject("listRoles", listRoles);
		editViewUser.addObject("user", user);
		logger.info("editing user data with id " + id);
		return editViewUser;
	}

	@PostMapping(path = "admin/users/save")
	public String updateUserData(User user) {
		userService.updateUser(user);
		logger.info("user is updated successfully");
		return "redirect:/users";
	}
}