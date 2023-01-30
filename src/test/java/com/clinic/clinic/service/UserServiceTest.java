package com.clinic.clinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.clinic.clinic.repository.RoleRepository;
import com.clinic.clinic.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository repository;

	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private UserService service;

	@Mock
	User userObject;

	@Test
	public void findUserByIdTest() {
		User user = new User("atharv", "atharv", "atharv", Set.of(new Role("User")));

		Mockito.when(repository.findUserById(anyLong())).thenReturn((user));
		User result = service.findById(1L);
		assertEquals(user.getUsername(), result.getUsername());
		assertEquals(user.getFullname(), result.getFullname());
	}

	@Test
	public void findUserByNameTest() {
		User user = new User("atharv", "atharv", "atharv", Set.of(new Role("User")));
		Mockito.when(repository.findByUsername(anyString())).thenReturn((user));
		User result = service.findByUsername("atharv");
		assertEquals(user.getUsername(), result.getUsername());
		assertEquals(user.getFullname(), result.getFullname());
	}

	@Test
	public void findAllUserTest() {
		User user = new User("atharv", "atharv", "atharv", Set.of(new Role("User")));
		Mockito.when(repository.findAll()).thenReturn((List.of(user)));
		List<User> result = service.findAllUsers();
		assertEquals(user.getUsername(), result.get(0).getUsername());
		assertEquals(user.getFullname(), result.get(0).getFullname());
	}

	@Test
	public void getPetsSaveTest() {
		User user = new User("atharv", "atharv", "atharv", Set.of(new Role("User")));
		Mockito.when(repository.save(any(User.class))).thenReturn((user));
		service.saveUser(user);
		verify(repository, times(1)).save(user);
	}


	@Test
	public void getUserUpdateTest() {
		User user = new User("atharv", "atharv", "atharv", Set.of(new Role("User")));
		Mockito.when(repository.save(any(User.class))).thenReturn((user));
		service.updateUser(user);
		verify(repository, times(1)).save(user);
	}

	@Test
	public void getAllRolesTest() {
		Mockito.when(roleRepository.findAll()).thenReturn((List.of(new Role("User"))));
		List<Role> result = service.listRoles();
		assertEquals("User", result.get(0).getName());
	}
}