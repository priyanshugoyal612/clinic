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
public class PetControllerTest {

	@Mock
	public PetService petService;

	
	@InjectMocks
	private PetController controller;

	@Mock
	Model model;

	@Mock
	SecurityContextHolder context;

	@Test
	public void showRegistrationFormPet() {
		String result = controller.showRegistrationFormPet(model);
		assertEquals("signup_form_pet", result);
	}

	@Test
	public void updatePetTest() {
		doNothing().when(petService).update(any(Pet.class));
		String result = controller.updatePet(new Pet());
		assertEquals("redirect:/pets", result);
	}

	@Test
	public void deletePetTest() {
		doNothing().when(petService).deletePetById(anyLong());
		String result = controller.deletePet(1L);
		assertEquals("redirect:/pets", result);
	}

	@Test
	public void showEditFormPetTest() {
		Mockito.when(petService.findById(anyLong())).thenReturn(new Pet());
		ModelAndView modelAndView = controller.showEditFormPet(1L);
		assertEquals("edit-pets", modelAndView.getViewName());
	}
}