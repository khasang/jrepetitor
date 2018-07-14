package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.impl.QuizPreviewDTOImpl;
import io.khasang.jrepetitor.entity.Group;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.model.wrappers.GroupWrapper;
import io.khasang.jrepetitor.model.wrappers.QuizByGroupIdRequestWrapper;
import org.junit.Test;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static io.khasang.jrepetitor.util.TestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class QuizControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080";
    private static final String QUIZ = "/quiz";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";
    private static final String PREVIEW = "/preview";

    @Test
    public void addQuizAndCheck() {
        GroupWrapper groupWrapper = prefillGroup("group_name");
        Group group = createGroup(groupWrapper);
        QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper = prefillQuizStructure(
                group.getId(),
                "quiz_name",
                (byte) 1
        );
        Quiz quiz = createQuiz(quizByGroupIdRequestWrapper);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Quiz> responseEntity = template.exchange(
                ROOT + QUIZ + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Quiz.class,
                quiz.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Quiz receivedQuiz = responseEntity.getBody();
        assertNotNull(receivedQuiz);

        deleteGroupFromDB(group);
    }

    @Test
    public void getAllQuizzes() {
        GroupWrapper groupWrapper = prefillGroup("group_name");
        Group group = createGroup(groupWrapper);
        QuizByGroupIdRequestWrapper firstQuizByGroupIdRequestWrapper = prefillQuizStructure(
                group.getId(),
                "first_quiz_name",
                (byte) 1
        );

        QuizByGroupIdRequestWrapper secondQuizByGroupIdRequestWrapper = prefillQuizStructure(
                group.getId(),
                "second_quiz_name",
                (byte) 1
        );

        Quiz firstQuiz = createQuiz(firstQuizByGroupIdRequestWrapper);
        Quiz secondQuiz = createQuiz(firstQuizByGroupIdRequestWrapper);

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Quiz>> responseEntity = template.exchange(
                ROOT + QUIZ + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Quiz>>() {
                }
        );

        List<Quiz> list = responseEntity.getBody();

        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteGroupFromDB(group);
    }

    @Test
    public void removeTest() {
        GroupWrapper groupWrapper = prefillGroup("group_name");
        Group group = createGroup(groupWrapper);
        QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper = prefillQuizStructure(
                group.getId(),
                "quiz_name",
                (byte) 1
        );
        Quiz quiz = createQuiz(quizByGroupIdRequestWrapper);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Quiz> responseEntity = restTemplate.exchange(
                ROOT + QUIZ + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Quiz.class,
                quiz.getId()
        );
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(responseEntity.getBody().getId(), quiz.getId());
    }

    @Test
    public void previewGetTest() {
        GroupWrapper groupWrapper = prefillGroup("group_name");
        Group group = createGroup(groupWrapper);
        QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper = prefillQuizStructure(
                group.getId(),
                "quiz_name",
                (byte) 1
        );
        Quiz quiz = createQuiz(quizByGroupIdRequestWrapper);
        RestTemplate template = new RestTemplate();
        ResponseEntity<QuizPreviewDTOImpl> responseEntity = template.exchange(
                ROOT + QUIZ + PREVIEW + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                QuizPreviewDTOImpl.class,
                quiz.getId()
        );
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(quiz.getId(), responseEntity.getBody().getId());
        deleteGroupFromDB(group);
    }

    @Test
    public void previewGetAllTest() {
        GroupWrapper groupWrapper = prefillGroup("group_name");
        Group group = createGroup(groupWrapper);
        QuizByGroupIdRequestWrapper firstQuizByGroupIdRequestWrapper = prefillQuizStructure(
                group.getId(),
                "first_quiz_name",
                (byte) 1
        );

        QuizByGroupIdRequestWrapper secondQuizByGroupIdRequestWrapper = prefillQuizStructure(
                group.getId(),
                "second_quiz_name",
                (byte) 1
        );

        Quiz firstQuiz = createQuiz(firstQuizByGroupIdRequestWrapper);
        Quiz secondQuiz = createQuiz(firstQuizByGroupIdRequestWrapper);

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<QuizPreviewDTOImpl>> responseEntity = template.exchange(
                ROOT + QUIZ + PREVIEW + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<QuizPreviewDTOImpl>>() {
                }
        );

        List<QuizPreviewDTOImpl> list = responseEntity.getBody();
        assertEquals(firstQuiz.getId(), list.get(0).getId());
        assertEquals(secondQuiz.getId(), list.get(1).getId());

        deleteGroupFromDB(group);
    }
}
