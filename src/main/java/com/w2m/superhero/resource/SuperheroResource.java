package com.w2m.superhero.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.superhero.annotations.interfaces.MethodInfo;
import com.w2m.superhero.dto.ResponseDTO;
import com.w2m.superhero.dto.SuperheroDTO;
import com.w2m.superhero.jpa.SuperheroEntity;
import com.w2m.superhero.repository.SuperheroRepository;
import com.w2m.superhero.service.SuperheroService;

import io.swagger.annotations.Api;

@RestController
@Path("/superheroes")
@Api(value = "superheroes", tags = { "superheroes" })
public class SuperheroResource {

	@Autowired
	private SuperheroRepository superheroRepository;

	@Autowired
	private SuperheroService superheroService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Cacheable(cacheNames="getAll")
	@MethodInfo
	public List<SuperheroEntity> getAll() {
		return superheroRepository.findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Cacheable(cacheNames="getById", condition="#id > 0")
	@MethodInfo
	public SuperheroEntity getById(@PathParam("id") int id) {
		return superheroRepository.findById(id);
	}

	@GET
	@Path("/names/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Cacheable(cacheNames="getByName")
	@MethodInfo
	public List<SuperheroEntity> getByName(@PathParam("name") String name) {
		return superheroRepository.findByNameContainsIgnoreCase(name);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@CacheEvict(value = { "getAll", "getById", "getByName" }, allEntries = true)
	@MethodInfo
	public ResponseDTO save(@RequestBody SuperheroDTO superheroDTO) {
		return superheroService.validateAndSaveHero(superheroDTO);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CacheEvict(value = { "getAll", "getById", "getByName" }, allEntries = true)
	@MethodInfo
	public ResponseDTO update(@PathParam("id") Integer id, @RequestBody SuperheroDTO superheroDTO) {
		return superheroService.validateAndUpdateHero(id, superheroDTO);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CacheEvict(value = { "getAll", "getById", "getByName" }, allEntries = true)
	@MethodInfo
	public ResponseDTO delete(@PathParam("id") Integer id) {
		return superheroService.validateAndDeleteHero(id);
	}
	
}
