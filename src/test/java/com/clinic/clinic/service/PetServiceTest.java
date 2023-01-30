package com.clinic.clinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.clinic.clinic.entity.Pet;
import com.clinic.clinic.entity.Role;
import com.clinic.clinic.entity.User;
import com.clinic.clinic.entity.enums.PetType;
import com.clinic.clinic.repository.PetRepository;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

	@Mock
	private PetRepository repository;

	@InjectMocks
	private PetService service;

	@Test
	public void getPetByIdTest() {
		Pet pet = new Pet();
		pet.setId(1L);
		pet.setType(PetType.DOG);
		pet.setName("Tiger");
		Mockito.when(repository.findPetById(anyLong())).thenReturn((pet));
		Pet result = service.findById(1L);
		assertEquals(pet.getName(), result.getName());
		assertEquals(pet.getType(), result.getType());
	}

	@Test
	public void getPetsUpdateTest() {
		Pet pet = new Pet();
		pet.setId(2L);
		pet.setType(PetType.DOG);
		pet.setName("Tiger");
		Mockito.when(repository.findPetById(anyLong())).thenReturn((pet));
		Mockito.when(repository.save(any(Pet.class))).thenReturn((pet));
		service.update(pet);
		verify(repository, times(1)).save(pet);
	}

	@Test
	public void getPetsDeleteTest() {
		Pet pet = new Pet();
		pet.setId(2L);
		pet.setType(PetType.DOG);
		pet.setName("Tiger");
		doNothing().when(repository).deleteById(anyLong());
		service.deletePetById(2L);
		verify(repository, times(1)).deleteById(anyLong());
	}

	@Test
	public void getFindByUserTest() {
		User user = new User("atharv", "atharv", "atharv", Set.of(new Role("User")));
		Pet pet = new Pet();
		pet.setId(2L);
		pet.setType(PetType.DOG);
		pet.setName("Tiger");
		Mockito.when(repository.findByUser(any(User.class))).thenReturn((List.of(pet)));
		List<Pet> result = service.findByUser(user);
		assertEquals(pet.getName(), result.get(0).getName());
		assertEquals(pet.getType(), result.get(0).getType());

	}

	@Test
	public void getPetsListTest() {
		Pet pet = new Pet();
		pet.setId(2L);
		pet.setType(PetType.DOG);
		pet.setName("Tiger");
		Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(pet));
		List<Pet> result = service.findAllPets();
		assertEquals(pet.getName(), result.get(0).getName());
		assertEquals(pet.getId(), result.get(0).getId());
	}
	
	@Test
	public void getPetsSaveTest() {
		Pet pet = new Pet();
		pet.setId(2L);
		pet.setType(PetType.DOG);
		pet.setName("Tiger");
		Mockito.when(repository.save(any(Pet.class))).thenReturn((pet));
		service.save(pet);
		verify(repository, times(1)).save(pet);
	}
	
}