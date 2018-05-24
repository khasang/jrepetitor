package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Quiz;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class QuizControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/quiz";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addQuizAndCheck() {
        Quiz quiz = createQuiz();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Quiz> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Quiz.class,
                quiz.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Quiz receivedQuiz = responseEntity.getBody();
        assertNotNull(receivedQuiz);

        deleteFromDB(quiz);
    }


    @Test
    public void getAllQuizs() {
        Quiz firstQuiz = createQuiz();
        Quiz secondQuiz = createQuiz();

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Quiz>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Quiz>>() {
                }
        );

        List<Quiz> list = responseEntity.getBody();

        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstQuiz);
        deleteFromDB(secondQuiz);
    }

    public Quiz deleteFromDB(Quiz quiz) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Quiz> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Quiz.class,
                quiz.getId()
        );

        return responseEntity.getBody();
    }

    private Quiz createQuiz() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Quiz quiz = prefillQuiz();

        HttpEntity entity = new HttpEntity(quiz, headers);

        RestTemplate template = new RestTemplate();

        Quiz receivedQuiz = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Quiz.class
        ).getBody();

        assertNotNull(receivedQuiz.getName());
        assertEquals(quiz.getName(), receivedQuiz.getName());

        return receivedQuiz;
    }

    private Quiz prefillQuiz() {
        Quiz quiz = new Quiz();
        quiz.setName("Generics and Collections");
        return quiz;
    }
}
