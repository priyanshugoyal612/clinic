package com.clinic.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.clinic.entity.Pet;
import com.clinic.clinic.entity.User;

/**
 * @author priyanshu.goyal
 *
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
	List<Pet> findByUser(User user);

	Pet findPetById(Long id);

}
