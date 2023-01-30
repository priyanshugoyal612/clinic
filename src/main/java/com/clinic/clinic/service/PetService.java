package com.clinic.clinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.clinic.entity.Pet;
import com.clinic.clinic.entity.User;
import com.clinic.clinic.repository.PetRepository;

/**
 * This is the pet service which  interact with user action and returns the
 * views/data and interact with database repository Its contain also the business
 * logic related to pet in the application
 * 
 * @author priyanshu.goyal
 *
 */
@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;

	/**
	 * This method find the pet on the basis of id from the database
	 * 
	 * @param id
	 * @return
	 */
	public Pet findById(Long id) {
		return petRepository.findPetById(id);
	}

	/**
	 * This method find all the pets registered with application
	 * 
	 * @return List<Pet>
	 */
	public List<Pet> findAllPets() {
		return petRepository.findAll();
	}

	/**
	 * This method update the pet information in the applications
	 * 
	 * @param pet
	 */
	public void update(Pet pet) {
		Pet petFromDB = petRepository.findPetById(pet.getId());
		petFromDB.setName(pet.getName());
		petFromDB.setType(pet.getType());
		petRepository.save(petFromDB);
	}

	/**
	 * This method delete the pet on the basis of id from the application
	 * 
	 * @param id
	 */
	public void deletePetById(Long id) {
		petRepository.deleteById(id);
	}

	/**
	 * This method find the pet on the basis of user ownership
	 * 
	 * @param user
	 * @return List<Pet>
	 */
	public List<Pet> findByUser(User user) {
		return petRepository.findByUser(user);
	}

	/**
	 * This method save the new pet data in the application database
	 * 
	 * @param pet
	 */
	public void save(Pet pet) {
		petRepository.save(pet);
	}
}
