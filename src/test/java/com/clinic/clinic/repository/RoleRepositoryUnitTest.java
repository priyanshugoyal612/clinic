package com.clinic.clinic.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.clinic.clinic.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryUnitTest {

	@Autowired
	RoleRepository repo;

	@Test
	public void testCreateRoles() {
		Role normal = new Role("Normal");
		Role admin = new Role("Admin");

		repo.saveAll(List.of(normal, admin));
		List<Role> listRoles = repo.findAll();
		assertThat(listRoles.size()).isEqualTo(2);
	}

}
