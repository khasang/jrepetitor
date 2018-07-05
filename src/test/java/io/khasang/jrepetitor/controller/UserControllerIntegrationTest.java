package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.model.wrappers.CreationProfileStatusResponseWrapper;
import io.khasang.jrepetitor.model.wrappers.CreationUserStatusResponseWrapper;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static io.khasang.jrepetitor.util.TestUtils.*;

import java.util.List;

import static org.junit.Assert.*;


public class UserControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/users";
    private static final String ADD = "/add";
    private static final String CREATE = "/create";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";
    private static final String AUTHORIZED = "/authorized";
    private static final String PROFILE = "/profile";

    /*
     Run only on clear base (unique db fields used)
     This test can damage data in the database.
     Test must be incorrect if u have record with id = LongInteger.MaxValue
     */

    @Test
    public void addUserAndCheck() {
        User user = createUser("test",
                "test",
                "test",
                "test",
                "test",
                "test@domain.zone",
                "1234567890",
                "ROLE_USER",
                ROOT,
                ADD);

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

    @Test()
    public void deleteUser() {
        User user = createUser("test",
                "test",
                "test",
                "test",
                "test",
                "test@domain.zone",
                "1234567890",
                "ROLE_USER",
                ROOT,
                ADD);

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
    }


    @Test()
    public void deleteNotExistingUser() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<User> responseEntity = restTemplate.exchange(
                    ROOT + DELETE + "?id=" + "{id}",
                    HttpMethod.DELETE,
                    null,
                    User.class,
                    Long.MAX_VALUE
            );
        } catch (HttpClientErrorException e) {
            assertEquals(e.getMessage(), "404 null");
        }
    }

    @Test()
    public void getByIdNotExistingUser() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<User> responseForGetUser = restTemplate.exchange(
                    ROOT + GET_BY_ID + "/{id}",
                    HttpMethod.GET,
                    null,
                    User.class,
                    Long.MAX_VALUE
            );
        } catch (HttpClientErrorException e) {
            assertEquals(e.getMessage(), "404 null");
        }
    }

    @Test
    public void getAllUsers() {
        User firstUser = createUser("test1",
                "test",
                "test",
                "test",
                "test",
                "test1@domain.zone",
                "12345678901",
                "ROLE_USER",
                ROOT,
                ADD);
        User secondUser = createUser("test2",
                "test",
                "test",
                "test",
                "test",
                "test2@domain.zone",
                "12345678902",
                "ROLE_USER",
                ROOT,
                ADD);

        List<User> list = returnAllUsers();

        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstUser);
        deleteFromDB(secondUser);
    }

    @Test
    public void createUserTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = prefillUser("test1",
                "test",
                "test",
                "test",
                "test",
                "test1@domain.zone",
                "12345678901",
                "ROLE_USER");

        HttpEntity entity = new HttpEntity(user, headers);

        RestTemplate template = new RestTemplate();

        CreationUserStatusResponseWrapper creationUserStatusResponseWrapper = template.exchange(
                ROOT + CREATE,
                HttpMethod.POST,
                entity,
                CreationUserStatusResponseWrapper.class
        ).getBody();

        assertTrue(creationUserStatusResponseWrapper.isOk());
        assertFalse(creationUserStatusResponseWrapper.getEmailExist());
        assertFalse(creationUserStatusResponseWrapper.getLoginExist());
        assertFalse(creationUserStatusResponseWrapper.getPhoneExist());

        //try to create a user with already existing login, phone and email
        creationUserStatusResponseWrapper = template.exchange(
                ROOT + CREATE,
                HttpMethod.POST,
                entity,
                CreationUserStatusResponseWrapper.class
        ).getBody();

        assertFalse(creationUserStatusResponseWrapper.isOk());
        assertTrue(creationUserStatusResponseWrapper.getEmailExist());
        assertTrue(creationUserStatusResponseWrapper.getLoginExist());
        assertTrue(creationUserStatusResponseWrapper.getPhoneExist());

        List<User> users = returnAllUsers();

        //remove test user from db
        User searchedUser = null;
        for (User currentUser : users) {
            if (currentUser.getLogin().equals(user.getLogin()) &&
                    currentUser.getProfile().getPhoneNumber().equals(user.getProfile().getPhoneNumber()) &&
                    currentUser.getProfile().getEmail().equals(user.getProfile().getEmail())) {
                searchedUser = currentUser;
            }
        }
        if (searchedUser != null) {
            deleteFromDB(searchedUser);
        } else {
            throw new IllegalStateException("something wrong added user not exist in base");
        }

    }

    @Test
    public void authorizedAnonymousTest() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> responseEntity = template.exchange(
                ROOT + AUTHORIZED,
                HttpMethod.GET,
                null,
                String.class
        );
        assertEquals(responseEntity.getStatusCodeValue(), 200);
        assertEquals(responseEntity.getBody(), "anonymousUser");
    }

    @Test
    public void authorizedUserTest() {
        User user = createUser("test",
                "test",
                "test",
                "test",
                "test",
                "test@domain.zone",
                "1234567890",
                "ROLE_USER",
                ROOT,
                ADD);


        RestTemplate restTemplate = new RestTemplate();
        String sessionId = formAuth("test", "test");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(String.class, requestHeaders);
        ResponseEntity responseEntity = restTemplate.exchange(
                ROOT + AUTHORIZED,
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        assertEquals(responseEntity.getStatusCodeValue(), 200);
        assertEquals(user.getName(), (String) responseEntity.getBody());
        deleteFromDB(user);
    }

    @Test
    public void getProfileUnauthorized() {
        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<Profile> responseForGetUser = template.exchange(
                    ROOT + PROFILE,
                    HttpMethod.GET,
                    null,
                    Profile.class
            );
        } catch (HttpClientErrorException e) {
            assertEquals(e.getMessage(), "401 null");
        }
    }

    @Test
    public void getProfileAuthorized() {
        User user = createUser("test",
                "test",
                "test",
                "test",
                "test",
                "test@domain.zone",
                "1234567890",
                "ROLE_USER",
                ROOT,
                ADD);

        RestTemplate restTemplate = new RestTemplate();
        String sessionId = formAuth("test", "test");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(requestHeaders);

        ResponseEntity<Profile> responseForGetUser = restTemplate.exchange(
                ROOT + PROFILE,
                HttpMethod.GET,
                requestEntity,
                Profile.class
        );

        Profile returnedProfile = responseForGetUser.getBody();
        Profile existProfile = user.getProfile();
        assertEquals(responseForGetUser.getStatusCodeValue(), 200);
        assertEquals(returnedProfile.getEmail(), existProfile.getEmail());
        assertEquals(returnedProfile.getPhoneNumber(), existProfile.getPhoneNumber());
        assertEquals(returnedProfile.getName(), existProfile.getName());
        assertEquals(returnedProfile.getSurname(), existProfile.getSurname());
        assertEquals(returnedProfile.getMiddlename(), existProfile.getMiddlename());
        deleteFromDB(user);
    }

    @Test
    public void setProfileUserUnauthorized() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            Profile profile = new Profile();

            HttpEntity entity = new HttpEntity(profile, headers);

            RestTemplate template = new RestTemplate();
            ResponseEntity<CreationUserStatusResponseWrapper> responseEntity = template.exchange(
                    ROOT + PROFILE,
                    HttpMethod.POST,
                    entity,
                    CreationUserStatusResponseWrapper.class
            );
        } catch (HttpClientErrorException e) {
            assertEquals(e.getMessage(), "401 null");
        }
    }

    @Test
    public void setProfileUserAuthorized() {
        User user = createUser("test",
                "test",
                "test",
                "test",
                "test",
                "test@domain.zone",
                "1234567890",
                "ROLE_USER",
                ROOT,
                ADD);

        Profile newProfile = new Profile();
        newProfile.setName("changed_name");
        newProfile.setSurname("changed_surname");
        newProfile.setMiddlename("changed_middle_name");
        newProfile.setPhoneNumber("changed_phone");
        newProfile.setEmail("changed_email");

        RestTemplate restTemplate = new RestTemplate();
        String sessionId = formAuth("test", "test");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(newProfile, requestHeaders);

        RestTemplate template = new RestTemplate();
        ResponseEntity<CreationProfileStatusResponseWrapper> responseEntity = template.exchange(
                ROOT + PROFILE,
                HttpMethod.POST,
                requestEntity,
                CreationProfileStatusResponseWrapper.class
        );

        assertEquals(responseEntity.getBody().getPhoneExist(), false);
        assertEquals(responseEntity.getBody().getEmailExist(), false);
        assertEquals(responseEntity.getStatusCodeValue(), 200);
        deleteFromDB(user);
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

    private List<User> returnAllUsers() {
        RestTemplate template = new RestTemplate();

        ResponseEntity<List<User>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        );

        List<User> list = responseEntity.getBody();
        return list;
    }
}
