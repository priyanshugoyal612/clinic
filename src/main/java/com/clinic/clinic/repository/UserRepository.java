package com.clinic.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clinic.clinic.entity.Pet;
import com.clinic.clinic.entity.User;


/**
 * @author priyanshu.goyal
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findUserById(Long id);
	User findByUsername(String username);
	 
}
