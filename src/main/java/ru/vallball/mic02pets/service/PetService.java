package ru.vallball.mic02pets.service;

import java.util.List;

import ru.vallball.mic02pets.model.Pet;

public interface PetService {
	void save(Pet pet);

	List<Pet> list();

	void delete(Long id);

	Pet findById(Long id);
}
