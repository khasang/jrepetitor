package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.User;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AdmControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/adm";
    private static final String ADD = "/add";
    private static final String ALL_USERS = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String GET_BY_NAME = "/get/name";
    private static final String DELETE = "/delete";
    private static final int LIST_OF_USERS_SIZE = 2;

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

        deleteUserFromDB(user);
    }

    @Test
    public void getAllUsers() {
        List<Long> listNewId = new ArrayList<>();

        for (int i = 0; i < LIST_OF_USERS_SIZE; i++) {
            User user = createUser();
            listNewId.add(user.getId());
        }

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<User>> responseEntity = template.exchange(
                ROOT + ALL_USERS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        );

        List<User> list = responseEntity.getBody();

        list.forEach(user -> {
            assertNotNull(user);
            if (user != null && listNewId.contains(user.getId())) {
                deleteUserFromDB(user);
            }
        });
    }

    @Test
    public void deleteUser() {
        User user = createUser();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                User.class,
                user.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        User deletedUser = responseEntity.getBody();
        assertNotNull(deletedUser);

        ResponseEntity<User> responseForDeleteUser = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                User.class,
                deletedUser.getId()
        );

        assertEquals(200, responseForDeleteUser.getStatusCodeValue());

        assertNull(responseForDeleteUser.getBody());
    }

    @Test
    public void getUsersByName() {
        final String defineName = "Anatoly";

        List<User> users = Arrays.asList(
                createUser(defineName, "an", "tr1234"),
                createUser("Irina", "ir", "gd54it"),
                createUser(defineName, "tws", "tt234"),
                createUser("Ivan", "iv", "4r32")
        );

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<User>> responseEntity = template.exchange(
                ROOT + GET_BY_NAME + "/{user}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {},
                defineName
        );

        List<User> receivedUsers = responseEntity.getBody();

        assertNotNull(receivedUsers);
        assertEquals(2, receivedUsers.size());
        receivedUsers.forEach(user -> assertEquals(defineName, user.getName()));

        users.forEach(this::deleteUserFromDB);
    }

    @Ignore
    @Test(expected = org.springframework.web.client.HttpServerErrorException.class)
    public void checkUserAdditionForRepeatingLogin() {
        final String defineLogin = "superUser";

        List<User> users = Arrays.asList(
                createUser("Ilia", defineLogin, "tr1234"),
                createUser("Pete", defineLogin, "tt234")
        );

        users.forEach(this::deleteUserFromDB);
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

    private User createUser(String name, String login, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = prefillUser(name, login, password);

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
        user.setName("UserDEL");
        user.setLogin("cool_man");
        user.setPassword("123456");
        return user;
    }

    private User prefillUser(String name, String login, String password) {
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        return user;
    }

    private User deleteUserFromDB(User user) {
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
