package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Cat;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

public class CatControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/cat";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get/";
    private static final String DELETE = "/delete/?id="+"{id}";
    private static final String GET_ALL = "/all";
    private static final String UPDATE = "/update";

    @Test
    public void deleteCat()
    {
        Cat cat = createCat();

        Cat deletedCat = deleteFromDB(cat);
        Assert.assertNotNull(deletedCat);

    }

    @Test
    public void updateCat()
    {
        Cat cat = createCat();
        HttpHeaders http = new HttpHeaders();
        http.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity(cat, http);
        cat.setName("UpdatestCat");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity <Cat> httpEntity = restTemplate.exchange(
                ROOT+UPDATE,
                HttpMethod.PUT,
                entity,
                Cat.class
        );

        Cat updetedCat = httpEntity.getBody();
        Assert.assertNotNull(updetedCat);
        Assert.assertEquals(cat.getName(),updetedCat.getName());

       deleteFromDB(cat);

    }

    @Test
    public void getAllCatFromDB()
    {
        Cat cat1 = createCat();
        Cat cat2 = createCat();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Cat>> entity = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {

                }
        );

        List<Cat> list = entity.getBody();

        Assert.assertNotNull(list.get(1));
        Assert.assertNotNull(list.get(2));

        deleteFromDB(cat1);
        deleteFromDB(cat2);
    }

    @Test
    public void addAndCheckCat()
    {
      Cat cat =  createCat();
      RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cat> responseEntity = restTemplate.exchange(
                ROOT+GET_BY_ID+"/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                cat.getId()

        );

        Assert.assertEquals("OK",responseEntity.getStatusCode().getReasonPhrase());
        Cat recievedCat = responseEntity.getBody();
        Assert.assertNotNull(recievedCat);
        deleteFromDB(cat);
    }

    private Cat createCat() {
        HttpHeaders http = new HttpHeaders();
        http.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat();

        HttpEntity entity = new HttpEntity(cat, http);

        RestTemplate restTemplate = new RestTemplate();
        Cat recievedCat = restTemplate.exchange(
                ROOT+ADD,
                HttpMethod.POST,
                entity,
                Cat.class
        ).getBody();
        Assert.assertNotNull(recievedCat.getName());
        Assert.assertEquals(cat.getName(),recievedCat.getName());
        return recievedCat;
    }

    private Cat prefillCat() {

        Cat cat = new Cat();
        cat.setName("Belosnegka");
        return cat;
    }

    private Cat deleteFromDB(Cat cat)
    {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cat> responseEntity = restTemplate.exchange(
                ROOT+DELETE,
                HttpMethod.DELETE,
                null,
                Cat.class,
                cat.getId()

        );
        return responseEntity.getBody();
    }

}
