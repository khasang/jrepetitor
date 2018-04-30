package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.utils.CreationUserStatus;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;


public class UserControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/users";
    private static final String ADD = "/add";
    private static final String CREATE = "/create";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    //run only clear base (unique db fields used)

    @Test
    public void addUserAndCheck() {
        User user = createUser("test", "test", "test", "test", "test", "test@domain.zone", "1234567890");

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
        if (receivedUser != null) {
            deleteFromDB(receivedUser);
        }
    }

    @Test
    public void deleteUser() {
        User user = createUser("test", "test", "test", "test", "test", "test@domain.zone", "1234567890");

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

        ResponseEntity<User> responseForDeleteEmployee = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                User.class,
                deletedUser.getId()
        );

        assertEquals(200, responseForDeleteEmployee.getStatusCodeValue());

        //assertNull(responseForDeleteEmployee.getBody());
    }

    @Test
    public void getAllUsers() {
        User firstUser = createUser("test1", "test", "test", "test", "test", "test1@domain.zone", "12345678901");
        User secondUser = createUser("test2", "test", "test", "test", "test", "test2@domain.zone", "12345678902");

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<User>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        );

        List<User> list = responseEntity.getBody();

        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstUser);
        deleteFromDB(secondUser);
    }

    @Test
    public void createUserTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = prefillUser("test1", "test", "test",
                "test", "test", "test1@domain.zone", "12345678901");

        HttpEntity entity = new HttpEntity(user, headers);

        RestTemplate template = new RestTemplate();

        CreationUserStatus creationUserStatus = template.exchange(
                ROOT + CREATE,
                HttpMethod.POST,
                entity,
                CreationUserStatus.class
        ).getBody();

        assertTrue(creationUserStatus.isOk());
        assertFalse(creationUserStatus.getEmailExist());
        assertFalse(creationUserStatus.getLoginExist());
        assertFalse(creationUserStatus.getPhoneExist());

        creationUserStatus = template.exchange(
                ROOT + CREATE,
                HttpMethod.POST,
                entity,
                CreationUserStatus.class
        ).getBody();

        assertFalse(creationUserStatus.isOk());
        assertTrue(creationUserStatus.getEmailExist());
        assertTrue(creationUserStatus.getLoginExist());
        assertTrue(creationUserStatus.getPhoneExist());
    }


    private User deleteFromDB(User user) {
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

    private User createUser(String login, String name, String pass, String middlename, String surname, String email,
                            String phoneNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = prefillUser(login, name, pass, middlename, surname, email, phoneNumber);

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

    private User prefillUser(String login, String name, String pass, String middlename, String surname, String email,
                             String phoneNumber) {
        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setPassword(pass);
        user.setRoleName("ROLE_USER");

        Profile profile = new Profile();
        profile.setName(name);
        profile.setMiddlename(middlename);
        profile.setSurname(surname);
        profile.setEmail(email);
        profile.setPhoneNumber(phoneNumber);

        user.setProfile(profile);
        return user;
    }

}
