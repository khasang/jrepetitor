package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.User;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AdmControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/adm";
    private static final String ADD = "/add";
    private static final String ALL_USERS = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addUserAndCheck() {
        User user = createUser();

        RestTemplate template = new RestTemplate();
        ResponseEntity<User> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                User.class,
                user.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        User receivedUser = responseEntity.getBody();
        assertNotNull(receivedUser);

        deleteUser(user);
    }

    private User createUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = prefillUser();

        HttpEntity entity = new HttpEntity(user, headers);

        RestTemplate template = new RestTemplate();

        User receivedUser = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                User.class
        ).getBody();

        assertNotNull(receivedUser.getName());
        assertEquals(user.getName(), receivedUser.getName());

        return receivedUser;
    }

    private User prefillUser() {
        User user = new User();
        user.setName("User");
        return user;
    }

    private User deleteUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                User.class,
                user.getId()
        );
        return responseEntity.getBody();
    }

}
