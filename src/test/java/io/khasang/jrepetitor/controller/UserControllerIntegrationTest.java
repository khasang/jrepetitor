package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Employee;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class UserControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/users";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    //run only clear base (unique db fields use)

    @Test
    public void addUserAndCheck() {
        User user = createUser("test", "test@domain.zone", "1234567890");

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

    //@Ignore
    @Test
    public void deleteUser() {
        User user = createUser("test", "test@domain.zone", "1234567890");

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
        User firstUser = createUser("test", "test@domain.zone", "1234567890");
        User secondUser = createUser("test1", "test1@domain.zone", "0987654321");

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Employee>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {
                }
        );

        List<Employee> list = responseEntity.getBody();

        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstUser);
        deleteFromDB(secondUser);
    }


    public User deleteFromDB(User user) {
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

    private User createUser(String login, String email, String phoneNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = prefillUser(login, email, phoneNumber);

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

    private User prefillUser(String login, String email, String phoneNumber) {
        User user = new User();
        user.setLogin(login);
        user.setName("test");
        user.setPassword("test");
        user.setRoleName("ROLE_TEST");

        Profile profile = new Profile();
        profile.setName("test");
        profile.setMiddlename("test");
        profile.setSurname("test");
        profile.setEmail(email);
        profile.setPhoneNumber(phoneNumber);

        user.setProfile(profile);
        return user;
    }

}
