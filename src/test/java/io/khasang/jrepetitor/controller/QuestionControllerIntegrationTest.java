package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Question;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class QuestionControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/question";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addQuestionAndCheck() {
        Question question = createQuestion();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Question> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Question.class,
                question.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Question receivedQuestion = responseEntity.getBody();
        assertNotNull(receivedQuestion);

        deleteFromDB(question);
    }

    @Test
    public void deleteQuestion() {
        Question question = createQuestion();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Question> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Question.class,
                question.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        Question deletedQuestion = responseEntity.getBody();
        assertNotNull(deletedQuestion);

        ResponseEntity<Question> responseForDeleteQuestion = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Question.class,
                deletedQuestion.getId()
        );

        assertEquals(200, responseForDeleteQuestion.getStatusCodeValue());
        assertNull(responseForDeleteQuestion.getBody());
    }

    @Test
    public void getAllQuestions() {
        Question firstQuestion = createQuestion();
        Question secondQuestion = createQuestion();

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Question>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Question>>() {
                }
        );

        List<Question> list = responseEntity.getBody();

        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstQuestion);
        deleteFromDB(secondQuestion);
    }

    public Question deleteFromDB(Question question) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Question> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Question.class,
                question.getId()
        );

        return responseEntity.getBody();
    }

    private Question createQuestion() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Question question = prefillQuestion();

        HttpEntity entity = new HttpEntity(question, headers);

        RestTemplate template = new RestTemplate();

        Question receivedQuestion = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Question.class
        ).getBody();

        assertNotNull(receivedQuestion.getContent());
        assertEquals(question.getContent(), receivedQuestion.getContent());

        return receivedQuestion;
    }

    private Question prefillQuestion() {
        Question question = new Question();
        question.setType("RadioGroup");
        question.setContent("Does list allows duplicates?");
        question.setExplanation("Yes, it does. Follow link ...");
        return question;
    }
}
