package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.entity.UserAnswer;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class UserAnswerControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/userAnswer";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addUserAnswerAndCheck() {
        UserAnswer userAnswer = createUserAnswer();

        RestTemplate template = new RestTemplate();
        ResponseEntity<UserAnswer> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                UserAnswer.class,
                userAnswer.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        UserAnswer receivedUserAnswer = responseEntity.getBody();
        assertNotNull(receivedUserAnswer);

        deleteFromDB(userAnswer);
    }

    @Test
    public void deleteUserAnswer() {
        UserAnswer userAnswer = createUserAnswer();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserAnswer> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                UserAnswer.class,
                userAnswer.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        UserAnswer deletedUserAnswer = responseEntity.getBody();
        assertNotNull(deletedUserAnswer);

        ResponseEntity<UserAnswer> responseForDeleteUserAnswer = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                UserAnswer.class,
                deletedUserAnswer.getId()
        );

        assertEquals(200, responseForDeleteUserAnswer.getStatusCodeValue());

        assertNull(responseForDeleteUserAnswer.getBody());
    }

    @Test
    public void getAllUserAnswers() {
        UserAnswer firstUserAnswer = createUserAnswer();
        UserAnswer secondUserAnswer = createUserAnswer();

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<UserAnswer>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserAnswer>>() {
                }
        );

        List<UserAnswer> list = responseEntity.getBody();

        // логика хромает
        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstUserAnswer);
        deleteFromDB(secondUserAnswer);
    }

    public UserAnswer deleteFromDB(UserAnswer userAnswer) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserAnswer> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                UserAnswer.class,
                userAnswer.getId()
        );

        return responseEntity.getBody();
    }

    private UserAnswer createUserAnswer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        UserAnswer userAnswer = prefillUserAnswer();

        HttpEntity entity = new HttpEntity(userAnswer, headers);

        RestTemplate template = new RestTemplate();

        UserAnswer receivedUserAnswer = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                UserAnswer.class
        ).getBody();

        assertNotNull(receivedUserAnswer.getQuestion());
        assertEquals(userAnswer.getQuestion(), receivedUserAnswer.getQuestion());

        return receivedUserAnswer;
    }

    private UserAnswer prefillUserAnswer() {
        UserAnswer userAnswer = new UserAnswer();
        Quiz quiz = new Quiz();
        quiz.setName("Java1");
        userAnswer.getQuestion();
        return userAnswer;
    }
}
