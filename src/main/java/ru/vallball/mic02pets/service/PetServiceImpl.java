package ru.vallball.mic02pets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.mic02pets.dao.PetRepository;
import ru.vallball.mic02pets.model.Pet;

@Service
@Transactional
public class PetServiceImpl implements PetService{
	
	@Autowired
	PetRepository petRepository;

	@Override
	public void save(Pet pet) {
		petRepository.save(pet);		
	}

	@Override
	public List<Pet> list() {
		return petRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		petRepository.deleteById(id);
	}

	@Override
	public Pet findById(Long id) {
		return petRepository.findById(id).get();
	}

}
