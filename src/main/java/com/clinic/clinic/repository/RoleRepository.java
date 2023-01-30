package com.clinic.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.clinic.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String string);

}
