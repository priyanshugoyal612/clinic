package com.clinic.clinic.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.clinic.clinic.entity.Pet;
import com.clinic.clinic.entity.User;
import com.clinic.clinic.entity.enums.PetType;
import com.clinic.clinic.service.PetService;
import com.clinic.clinic.service.UserService;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

	@Mock
	public PetService petService;

	@Mock
	public UserService userService;

	@InjectMocks
	private AdminController controller;

	@Mock
	Model model;

	@Mock
	SecurityContextHolder context;

	@Test
	public void getAdminHome() {
		String result = controller.adminHome();
		assertEquals("admin", result);
	}

	@Test
	public void getStatisticTest() {
		Pet pet = new Pet();
		pet.setId(1L);
		pet.setType(PetType.DOG);
		pet.setName("Tiger");
		Mockito.when(petService.findAllPets()).thenReturn(List.of(pet));
		String result = controller.getStatistic(model);
		assertEquals("statistic", result);
	}

	@Test
	public void showRegistrationFormTest() {
		String result = controller.listAllPets(model);
		assertEquals("pets", result);
	}

	@Test
	public void listUsersTest() {
		String result = controller.listUsers(model);
		assertEquals("users", result);
	}

	@Test
	public void updateUserDataTest() {
		doNothing().when(userService).updateUser(any(User.class));
		String result = controller.updateUserData(new User());
		assertEquals("redirect:/users", result);
	}

	@Test
	public void showEditFormUserTest() {
		Mockito.when(userService.findById(anyLong())).thenReturn(new User());
		ModelAndView modelAndView = controller.showEditFormUser(1L);
		assertEquals("edit-user", modelAndView.getViewName());
	}

}
