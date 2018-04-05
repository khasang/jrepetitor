package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.UserTry;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class UserTryControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/userTry";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addUserTryAndCheck() {
        UserTry userTry = createUserTry();

        RestTemplate template = new RestTemplate();
        ResponseEntity<UserTry> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                UserTry.class,
                userTry.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        UserTry receivedUserTry = responseEntity.getBody();
        assertNotNull(receivedUserTry);

        deleteFromDB(userTry);
    }

    @Test
    public void deleteUserTry() {
        UserTry userTry = createUserTry();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserTry> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                UserTry.class,
                userTry.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        UserTry deletedUserTry = responseEntity.getBody();
        assertNotNull(deletedUserTry);

        ResponseEntity<UserTry> responseForDeleteUserTry = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                UserTry.class,
                deletedUserTry.getId()
        );

        assertEquals(200, responseForDeleteUserTry.getStatusCodeValue());

        assertNull(responseForDeleteUserTry.getBody());
    }

    @Test
    public void getAllUserTrys() {
        UserTry firstUserTry = createUserTry();
        UserTry secondUserTry = createUserTry();

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<UserTry>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserTry>>() {
                }
        );

        List<UserTry> list = responseEntity.getBody();

        // логика хромает
        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstUserTry);
        deleteFromDB(secondUserTry);
    }

    public UserTry deleteFromDB(UserTry userTry) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserTry> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                UserTry.class,
                userTry.getId()
        );

        return responseEntity.getBody();
    }

    private UserTry createUserTry() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        UserTry userTry = prefillUserTry();

        HttpEntity entity = new HttpEntity(userTry, headers);

        RestTemplate template = new RestTemplate();

        UserTry receivedUserTry = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                UserTry.class
        ).getBody();

        assertNotNull(receivedUserTry.getScore());
        assertEquals(userTry.getScore(), receivedUserTry.getScore());

        return receivedUserTry;
    }

    private UserTry prefillUserTry() {
        UserTry userTry = new UserTry();
        userTry.setScore(10);
        return userTry;
    }
}
