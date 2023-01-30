package com.clinic.clinic.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.clinic.clinic.entity.Pet;
import com.clinic.clinic.entity.User;
import com.clinic.clinic.service.PetService;
import com.clinic.clinic.service.UserService;

/**
 * This is the controller class for pet related functionality which uses user
 * service and pet service to manipulate the data
 * 
 * @author priyanshu.goyal
 *
 */
@Controller
public class PetController {
	private static final Logger logger = LoggerFactory.getLogger(PetController.class);

	@Autowired
	UserService userService;

	@Autowired
	PetService petService;

	/**
	 * This controller method returns the view for list of all pets registered by
	 * the user
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/pets")
	public String listPets(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails detail = (UserDetails) auth.getPrincipal();
		String username = detail.getUsername();
		User user = userService.findByUsername(username);
		List<Pet> listPets = petService.findByUser(user);
		model.addAttribute("listPets", listPets);
		logger.info("getting pets data");
		return "pets";
	}

	/**
	 * This controller method returns the registration form pet
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/registerpet")
	public String showRegistrationFormPet(Model model) {
		model.addAttribute("pet", new Pet());
		logger.info("getting pet registration form");
		return "signup_form_pet";
	}

	/**
	 * This controller method save the new registered pet with the application and
	 * return the view for list of all pets registered by users
	 * 
	 * @param pet
	 * @return
	 */
	@PostMapping("/process_register_pet")
	public String processRegisterPet(@ModelAttribute("pet") Pet pet) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails detail = (UserDetails) auth.getPrincipal();
		User user = userService.findByUsername(detail.getUsername());
		pet.setUser(user);
		petService.save(pet);
		logger.info("successfully pet is registered with pet name " + pet.getName());
		return "redirect:/pets";
	}

	/**
	 * This controller returns update form for the pet information registered by the
	 * user
	 * 
	 * @param id
	 * @return ModelAndView
	 */
	@GetMapping(path = "/edit/{id}")
	public ModelAndView showEditFormPet(@PathVariable(name = "id") Long id) {
		ModelAndView editView = new ModelAndView("edit-pets");
		Pet pet = petService.findById(id);
		editView.addObject("pet", pet);
		logger.info("editing pet information");
		return editView;
	}

	/**
	 * This controller method update the pet information
	 * 
	 * @param pet
	 * @return
	 */
	@PostMapping("/update_pet")
	public String updatePet(@ModelAttribute("pet") Pet pet) {
		petService.update(pet);
		logger.info("updating pet infor");
		return "redirect:/pets";
	}

	/**
	 * This controller method delete the pet information registered by the user
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/delete_pet/{id}")
	public String deletePet(@PathVariable(name = "id") Long id) {
		petService.deletePetById(id);
		logger.info("pet  deleted successfully");
		return "redirect:/pets";
	}
}