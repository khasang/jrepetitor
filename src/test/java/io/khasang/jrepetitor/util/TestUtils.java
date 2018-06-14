package io.khasang.jrepetitor.util;

import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestUtils {
    public static User createUser(String login, String name, String pass, String middlename, String surname,
                                  String email, String phoneNumber, String root, String add) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = prefillUser(login, name, pass, middlename, surname, email, phoneNumber);

        HttpEntity entity = new HttpEntity(user, headers);

        RestTemplate template = new RestTemplate();

        User receivedUser = template.exchange(
                root + add,
                HttpMethod.POST,
                entity,
                User.class
        ).getBody();

        assertNotNull(receivedUser.getName());
        assertEquals(user.getName(), receivedUser.getName());

        return receivedUser;
    }

    public static User prefillUser(String login, String name, String pass, String middlename, String surname,
                                   String email, String phoneNumber) {
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
