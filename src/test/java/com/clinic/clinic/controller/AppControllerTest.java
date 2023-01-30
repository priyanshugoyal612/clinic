package com.clinic.clinic.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.clinic.clinic.entity.User;
import com.clinic.clinic.service.PetService;
import com.clinic.clinic.service.UserService;

@ExtendWith(MockitoExtension.class)
public class AppControllerTest {

	@Mock
	public PetService petService;

	@Mock
	public UserService userService;

	@InjectMocks
	private AppController controller;

	@Mock
	Model model;
	
	@Mock
	SecurityContextHolder context;

	@Test
	public void getHomePageTest() {
		String result = controller.getHomePage();
		assertEquals("index", result);
	}

	@Test
	public void getIndexTest() {
		String result = controller.getIndex();
		assertEquals("index", result);
	}

	@Test
	public void getClinicTest() {
		String result = controller.clinic();
		assertEquals("clinic", result);
	}

	@Test
	public void showRegistrationFormTest() {
		String result = controller.showRegistrationForm(model);
		assertEquals("signup_form", result);
	}

	@Test
	public void processRegisterTest() {
		User user = new User();
		String result = controller.processRegister(user);
		assertEquals("register_success", result);
	}

	@Test
	public void nameEditTest() {
		String result = controller.nameEdit(model);
		assertEquals("name-edit", result);
	}

	/*
	 * @Test public void nameUpdate() { User user = new User(); String result =
	 * controller.nameUpdate(user); assertEquals("clinic", result); }
	 */

}
