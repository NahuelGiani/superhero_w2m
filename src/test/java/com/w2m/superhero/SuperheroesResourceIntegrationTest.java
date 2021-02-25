package com.w2m.superhero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.w2m.superhero.dto.SuperheroDTO;
import com.w2m.superhero.jpa.SuperheroEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuperheroProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperheroesResourceIntegrationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperheroesResourceIntegrationTest.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void testGetAllSuperheroes() {
		HttpEntity<String> entity = new HttpEntity<String>(null, new HttpHeaders());
		ResponseEntity<String> response = restTemplate.exchange(getBasePath(), HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetAllSuperheroesTotalSize() {
		SuperheroEntity[] superheroEntity = restTemplate.getForObject(getBasePath(), SuperheroEntity[].class);
		LOGGER.info("Superhero total found: {}", superheroEntity.length);
		assertNotEquals(superheroEntity.length, 0);
	}

	@Test
	public void testGetSuperheroById() {
		SuperheroEntity superheroEntity = restTemplate.getForObject(getBasePath() + "/1", SuperheroEntity.class);
		LOGGER.info("Superhero finded: {}", superheroEntity.getName());
		assertNotNull(superheroEntity);
	}

	@Test
	public void testGetSuperheroByName() {
		SuperheroEntity[] superheroEntity = restTemplate.getForObject(getBasePath() + "/names/man",
				SuperheroEntity[].class);
		LOGGER.info("Superhero found: {}", superheroEntity.toString());
		assertNotNull(superheroEntity);
	}

	@Test
	public void testCreateSuperhero() {
		SuperheroEntity superheroEntity = new SuperheroEntity();
		superheroEntity.setName("SUPERMAN");
		ResponseEntity<SuperheroEntity> postResponse = restTemplate.postForEntity(getBasePath(), superheroEntity,
				SuperheroEntity.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testUpdateSuperhero() {
		SuperheroDTO superheroDTO = new SuperheroDTO();
		superheroDTO.setName("JOKER");
		restTemplate.put(getBasePath() + "/" + 2, superheroDTO);
		SuperheroEntity superheroEntityGet = restTemplate.getForObject(getBasePath() + "/" + 2, SuperheroEntity.class);
		assertEquals(superheroEntityGet.getName(), "JOKER");
	}

	@Test
	public void testDeleteSuperhero() {
		SuperheroEntity superheroEntity = restTemplate.getForObject(getBasePath() + "/" + 1, SuperheroEntity.class);
		assertNotNull(superheroEntity);
		restTemplate.delete(getBasePath() + "/" + 1);
		SuperheroEntity superheroEntityGet = restTemplate.getForObject(getBasePath() + "/" + 1, SuperheroEntity.class);
		assertNull(superheroEntityGet);
	}

	private String getBasePath() {
		return "http://127.0.0.1:" + port + "/api/superheroes";
	}
}