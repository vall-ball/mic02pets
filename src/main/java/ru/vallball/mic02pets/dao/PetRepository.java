package ru.vallball.mic02pets.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.mic02pets.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{

}
