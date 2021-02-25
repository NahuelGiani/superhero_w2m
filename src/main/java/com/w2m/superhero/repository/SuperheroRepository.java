package com.w2m.superhero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w2m.superhero.jpa.SuperheroEntity;

public abstract interface SuperheroRepository extends JpaRepository<SuperheroEntity, Integer> {

	SuperheroEntity findById(int id);
	List<SuperheroEntity> findAll();
	List<SuperheroEntity> findByNameContainsIgnoreCase(String contains);

}