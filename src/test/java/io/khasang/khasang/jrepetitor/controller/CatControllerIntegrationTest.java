package io.khasang.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Cat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class CatControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/cat";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";

    @Before
    public void setup() {

    }

    @After
    public void clean() {

    }

    @Test
    public void addCatAndCheck() {
        Cat cat = createCat();
        RestTemplate template = new RestTemplate();
        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id }",
                HttpMethod.GET,
                null,
                Cat.class,
                cat.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Cat receivedCat = responseEntity.getBody();
        assertNotNull(receivedCat);
    }

    @Test
    public void deleteCat() {
        Cat cat = createCat();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cat> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Cat.class,
                cat.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        Cat deletedCat = responseEntity.getBody();
        assertNotNull(deletedCat);

        ResponseEntity<Cat> responseForDeleteCat = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                deletedCat.getId()
        );

        assertEquals(200, responseForDeleteCat.getStatusCodeValue());

        assertNull(responseForDeleteCat.getBody());
    }

    @Test
    public void getAllCats() {
        Cat firstCat = createCat();
        Cat secondCat = createCat();

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Cat>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {
                }
        );

        List<Cat> list = responseEntity.getBody();

        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstCat);
        deleteFromDB(secondCat);
    }

    @Test
    public void updateCatTest() {
        RestTemplate template = new RestTemplate();

        Cat cat = createCat();
        cat.setName("UpdatedBarsik");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity(cat, httpHeaders);
        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                entity,
                Cat.class,
                cat.getId()
        );
        Cat updatedCat = responseEntity.getBody();

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(updatedCat.getName(), cat.getName());
        assertEquals(updatedCat.getId(), cat.getId());
    }

    public Cat deleteFromDB(Cat cat) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cat> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Cat.class,
                cat.getId()
        );

        return responseEntity.getBody();
    }


    private Cat createCat() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat();

        HttpEntity entity = new HttpEntity(cat, httpHeaders);

        RestTemplate template = new RestTemplate();

        Cat receivedCat = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Cat.class
        ).getBody();
        assertNotNull(receivedCat.getName());
        assertEquals(cat.getName(), receivedCat.getName());
        return receivedCat;
    }

    private Cat prefillCat() {
        Cat cat = new Cat();
        cat.setName("Barsik");
        return cat;
    }

}
