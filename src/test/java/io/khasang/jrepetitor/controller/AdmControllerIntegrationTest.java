package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.News;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;

import io.khasang.jrepetitor.util.UserUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AdmControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/adm";
    // User const
    private static final String ADD = "/add";
    private static final String ALL_USERS = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String GET_BY_NAME = "/get/name";
    private static final String DELETE = "/delete";
    // News const
    private static final String ADD_NEWS = "/add/news";
    private static final String GET_NEWS_BY_ID = "/get/news";
    private static final String GET_NEWS_BY_TITLE = "/get/news/title";
    private static final String DELETE_NEWS = "/delete/news";

    // User test
    @Test
    public void addUserAndCheck() {
        User user = UserUtils.createUser("user",
                "user_name",
                "user_pass",
                "user_middlename",
                "user_surname",
                "user_email",
                "user_phone",
                ROOT,
                ADD
        );

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

        UserUtils.deleteUserFromDB(user, ROOT, DELETE);
    }


    @Test
    public void getAllUsers() {
        List<Long> listNewId = new ArrayList<>();
        User user1 = UserUtils.createUser("user_1",
                "user_name_1",
                "user_pass_1",
                "user_middlename_1",
                "user_surname_1",
                "user_email1_",
                "user_phone_1",
                ROOT,
                ADD
        );

        User user2 = UserUtils.createUser("user_2",
                "user_name_2",
                "user_pass_2",
                "user_middlename_2",
                "user_surname_2",
                "user_email_2",
                "user_phone_2",
                ROOT,
                ADD
        );


        listNewId.add(user1.getId());
        listNewId.add(user2.getId());

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
                UserUtils.deleteUserFromDB(user, ROOT, DELETE);
            }
        });
    }

    @Test
    public void deleteUser() {
        User user = UserUtils.createUser("user",
                "user_name",
                "user_pass",
                "user_middlename",
                "user_surname",
                "user_email",
                "user_phone",
                ROOT,
                ADD
        );

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
        final String defineName = "Searched_User";
        List<User> users = Arrays.asList(
                UserUtils.createUser("user_1",
                        defineName,
                        "user_pass_1",
                        "user_middlename_1",
                        "user_surname_1",
                        "user_email_1",
                        "user_phone_1",
                        ROOT,
                        ADD
                ),
                UserUtils.createUser("user_2",
                        defineName,
                        "user_pass_2",
                        "user_middlename_2",
                        "user_surname_2",
                        "user_email_2",
                        "user_phone_2",
                        ROOT,
                        ADD
                ),
                UserUtils.createUser("user_3",
                        "user_name_3",
                        "user_pass_3",
                        "user_middlename_3",
                        "user_surname_3",
                        "user_email_3",
                        "user_phone_3",
                        ROOT,
                        ADD
                ),
                UserUtils.createUser("user_4",
                        "user_name_4",
                        "user_pass_4",
                        "user_middlename_4",
                        "user_surname_4",
                        "user_email_4",
                        "user_phone_4",
                        ROOT,
                        ADD
                )
        );

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<User>> responseEntity = template.exchange(
                ROOT + GET_BY_NAME + "/{user}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                },
                defineName
        );

        List<User> receivedUsers = responseEntity.getBody();

        assertNotNull(receivedUsers);
        assertEquals(2, receivedUsers.size());
        receivedUsers.forEach(user -> assertEquals(defineName, user.getName()));
        receivedUsers.forEach(user -> UserUtils.deleteUserFromDB(user, ROOT, DELETE));
    }
    /*

    @Test
    public void checkUserAdditionForRepeatingLogin() {
        final String defineLogin = "superUser";
        Exception expectedException;

        List<User> users = new ArrayList<>();

        try {
            users.add(createUser("Ilia", defineLogin, "tr1234"));
            users.add(createUser("Pete", defineLogin, "tt234"));
        } catch (HttpServerErrorException e) {
            expectedException = e;
            assertNotNull(expectedException);
            users.forEach(this::deleteUserFromDB);
        }
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
    }*/


    // News test
    @Test
    public void addNewsAndCheck() {
        News news = createNews("Fresh news",
                "The very very new news :-) lkadfjlkajdlkajdlkfjaljdfjladjlfjakdj lakdjflkadjflkdjflkajfd" +
                        "alkdfjlakdjflkajdflkajdflkjadlfkjlakjflkajdlkfjalkjdlgkjlkgjljakljalkjdflkajdlfjlakjfljdlka" +
                        "akdfjkajflkjaldjfajkfdjlajfkjdalkfjdlkjfalkjdlkfajldkfjalkjdflakjflkadjflkadjflakjdflakjdfl" +
                        "alkdfjlkadjflajdlfkjaldjfkjadlfkjadlfkjaldkjflakdjflkajdflkajdflkajdflkjgkltjlkdajfjalkfjlj" +
                        "aldfkjladkfjlakdjflkadjflakdjflkadjlkjgkljklgjkjkladjflkdjfkjalkjfdkljalkjekjifldkjfakjldkf" +
                        "aldkfjladkfjleijfealkjdlkjdaflkdajflkdajflkadjflkdasjflkdjaflkjdaflkjaflkdjafldkjfldakjfl");

        RestTemplate template = new RestTemplate();

        ResponseEntity<News> responseEntity = template.exchange(
                ROOT + GET_NEWS_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                News.class,
                news.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        News receivedNews = responseEntity.getBody();
        assertNotNull(receivedNews);

        deleteNewsFromDB(receivedNews);
    }

    @Test
    public void getNewsByTitle() {
        final String defineTitle = "Weather news";

        List<News> news = Arrays.asList(
                createNews(defineTitle, "Moscow +15C"),
                createNews("Crime news", "Shooting in Chicago"),
                createNews(defineTitle, "San Francisco +78F")
        );

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<News>> responseEntity = template.exchange(
                ROOT + GET_NEWS_BY_TITLE + "/{news}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<News>>() {
                },
                defineTitle
        );

        List<News> receivedNews = responseEntity.getBody();

        assertNotNull(receivedNews);
        assertEquals(2, receivedNews.size());
        receivedNews.forEach(value -> assertEquals(defineTitle, value.getTitle()));

        news.forEach(this::deleteNewsFromDB);
    }

    @Test
    public void deleteNews() {
        News news = createNews("Weather", "Ryzan +17");

        RestTemplate template = new RestTemplate();
        ResponseEntity<News> responseEntity = template.exchange(
                ROOT + DELETE_NEWS + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                News.class,
                news.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        News deletedNews = responseEntity.getBody();
        assertNotNull(deletedNews);
        assertEquals(news.getId(), deletedNews.getId());
    }

    private News createNews(String title, String content) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        News news = prefillNews(title, content);

        HttpEntity<News> entity = new HttpEntity<>(news, headers);

        RestTemplate template = new RestTemplate();

        News receivedNews = template.exchange(
                ROOT + ADD_NEWS,
                HttpMethod.POST,
                entity,
                News.class
        ).getBody();

        return receivedNews;
    }

    private News prefillNews(String title, String content) {
        return new News(title, content);
    }

    private News deleteNewsFromDB(News news) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<News> responseEntity = restTemplate.exchange(
                ROOT + DELETE_NEWS + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                News.class,
                news.getId()
        );

        return responseEntity.getBody();
    }
}
