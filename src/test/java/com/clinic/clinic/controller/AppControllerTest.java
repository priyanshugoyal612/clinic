package com.clinic.clinic.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import com.clinic.clinic.authentication.CustomeUserDetails;
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

	@Mock
	Authentication auth;

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

	@Test
	public void nameUpdateTest() {
		User user = new User("priyanshu", "priyanshu", "priyanshu", null);
		CustomeUserDetails userDetails = new CustomeUserDetails(user);
		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);
		Mockito.when(userService.findByUsername(anyString())).thenReturn(user);
		doNothing().when(userService).saveUser(any(User.class));
		String result = controller.nameUpdate(user);
		assertEquals("clinic", result);
	}

}
