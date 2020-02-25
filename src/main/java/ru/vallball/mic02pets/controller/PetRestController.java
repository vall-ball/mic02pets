package ru.vallball.mic02pets.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.vallball.mic02pets.model.Pet;
import ru.vallball.mic02pets.service.PetService;

@RestController
@RequestMapping(value = "/pets", produces = "application/json")
public class PetRestController {
	
	@Autowired
	PetService petService;
	
	@GetMapping
	@ResponseBody
	public List<Pet> list() {
		return petService.list();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Pet get(@PathVariable(value = "id") Long id) {
		return petService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Pet pet) {
		petService.save(pet);
		return new ResponseEntity<>("Pet is created successfully", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody Pet pet) {
		try {
			Pet petForUpdate = petService.findById(id);
			petForUpdate.setName(pet.getName());
			petForUpdate.setSpecies(pet.getSpecies());
			petService.save(petForUpdate);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Pet not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Pet is udated successfully", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		try {
			petService.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Pet not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Pet is deleted successfully", HttpStatus.ACCEPTED);
	}


}
