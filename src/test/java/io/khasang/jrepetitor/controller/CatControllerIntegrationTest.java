package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Cat;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.*;

public class CatControllerIntegrationTest {
private static final String ROOT = "http://localhost:8080/cat";
private static final String ADD = "/add";
private static final String ALL = "/all";
private static final String GET_BY_ID = "/get";
private static final String DELETE = "/delete";

    @Test
    public void updateCat(){

    }

    @Test
    public void addCatAndCheck(){
        Cat cat = createCat();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                cat.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Cat receivedCat = responseEntity.getBody();
        assertNotNull(receivedCat);
    deleteFromDB(cat);
    }

    @Test
    public void deleteCat(){
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

        ResponseEntity<Cat> responseForDeletedCat = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                deletedCat.getId()
        );

        assertEquals(200, responseForDeletedCat.getStatusCodeValue());
        assertNull(responseForDeletedCat.getBody());
    }

    @Test
    public void getAllCats(){
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

    public Cat deleteFromDB(Cat cat){
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat();

        HttpEntity entity = new HttpEntity(cat, headers);

        RestTemplate template = new RestTemplate();

        Cat receivedCat = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Cat.class
        ).getBody();
        Assert.assertNotNull(receivedCat.getName());
        Assert.assertEquals(cat.getName(), receivedCat.getName());
        return receivedCat;
    }

    private Cat prefillCat() {
        Cat cat = new Cat();
        cat.setName("Barsik");
        return cat;
    }
}
