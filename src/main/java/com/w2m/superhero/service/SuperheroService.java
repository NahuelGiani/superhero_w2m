package com.w2m.superhero.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2m.superhero.dto.ResponseDTO;
import com.w2m.superhero.dto.SuperheroDTO;
import com.w2m.superhero.enums.StatusCode;
import com.w2m.superhero.jpa.SuperheroEntity;
import com.w2m.superhero.repository.SuperheroRepository;

@Service
public class SuperheroService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SuperheroService.class);

	@Autowired
	private SuperheroRepository superheroRepository;

	public ResponseDTO validateAndSaveHero(SuperheroDTO superheroDTO) {
		if(superheroDTO == null || StringUtils.isEmpty(superheroDTO.getName()))
			return new ResponseDTO(StatusCode.SUPERHERO_WITHOUT_NAME);
		
		SuperheroEntity superheroEntity = new SuperheroEntity(superheroDTO.getName());
		superheroRepository.save(superheroEntity);  
		return new ResponseDTO(StatusCode.SUPERHERO_SAVED_OK, superheroEntity);
	}

	
	public ResponseDTO validateAndUpdateHero(Integer id, SuperheroDTO superheroDTO){
		if(superheroDTO == null || StringUtils.isEmpty(superheroDTO.getName()))
			return new ResponseDTO(StatusCode.SUPERHERO_WITHOUT_NAME);

		if(id == null || !superheroRepository.existsById(id))
			return new ResponseDTO(StatusCode.SUPERHERO_DOESNT_EXIST, id);
		
		SuperheroEntity superheroEntity = superheroRepository.findById(id).get();
		superheroEntity.setName(superheroDTO.getName());
		superheroRepository.save(superheroEntity);
		
		return new ResponseDTO(StatusCode.SUPERHERO_UPDATED_OK, superheroEntity.toString());
	}
	
	public ResponseDTO validateAndDeleteHero(Integer id){
		if(id == null || !superheroRepository.existsById(id))
			return new ResponseDTO(StatusCode.SUPERHERO_DOESNT_EXIST, id);
		
		superheroRepository.deleteById(id);
		
		return new ResponseDTO(StatusCode.SUPERHERO_DELETED_OK);
	}
}
