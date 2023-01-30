package com.clinic.clinic.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.clinic.clinic.entity.Role;
import com.clinic.clinic.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryUnitTest {

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void newUsersTest() {
		User user = new User("riyanshu", "priyanshu", "priyanshu", Set.of(new Role("Admin")));
		User result = userRepo.save(user);
		User existingUser= entityManager.find(User.class, result.getId());
		assertThat(existingUser.getFullname()).isEqualTo(user.getFullname());
	}
	

	@Test
	public void testCreateUsers() {
		User user = new User("Priyanshu", "priyanshu", "priyanshu", Set.of(new Role("Admin")));
		User result = userRepo.save(user);
		assertThat(result.getRoles().size()).isEqualTo(1);
	}

}
