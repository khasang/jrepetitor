package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Group;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.model.wrappers.GroupWrapper;
import io.khasang.jrepetitor.model.wrappers.QuestionByQuizIdRequestWrapper;
import io.khasang.jrepetitor.model.wrappers.QuizByGroupIdRequestWrapper;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static io.khasang.jrepetitor.util.TestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class QuestionControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/question";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addQuestionAndCheck() {
        GroupWrapper groupWrapper = prefillGroup("group_name");
        Group group = createGroup(groupWrapper);
        QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper = prefillQuizStructure(
                group.getId(),
                "quiz_name",
                (byte) 1
        );
        Quiz quiz = createQuiz(quizByGroupIdRequestWrapper);
        QuestionByQuizIdRequestWrapper prefilledQuestion = prefillQuestion("Radio",
                "question_content",
                "question_explanation",
                quiz.getId());
        Question question = createQuestion(prefilledQuestion);

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

        deleteGroupFromDB(group);
    }

    @Test
    public void deleteQuestion() {
        GroupWrapper groupWrapper = prefillGroup("group_name");
        Group group = createGroup(groupWrapper);
        QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper = prefillQuizStructure(
                group.getId(),
                "quiz_name",
                (byte) 1
        );
        Quiz quiz = createQuiz(quizByGroupIdRequestWrapper);

        QuestionByQuizIdRequestWrapper prefilledQuestion = prefillQuestion("Radio",
                "question_content",
                "question_explanation",
                quiz.getId());
        Question question = createQuestion(prefilledQuestion);

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
        assertEquals(deletedQuestion.getId(), question.getId());

        try {
            ResponseEntity<Question> responseForDeleteQuestion = restTemplate.exchange(
                    ROOT + GET_BY_ID + "/{id}",
                    HttpMethod.GET,
                    null,
                    Question.class,
                    deletedQuestion.getId()
            );
        } catch (HttpClientErrorException e) {
            assertEquals(e.getMessage(), "404 null");
        }
        deleteGroupFromDB(group);
    }

    @Test
    public void getAllQuestions() {
        GroupWrapper groupWrapper = prefillGroup("group_name");
        Group group = createGroup(groupWrapper);
        QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper = prefillQuizStructure(
                group.getId(),
                "quiz_name",
                (byte) 1
        );
        Quiz quiz = createQuiz(quizByGroupIdRequestWrapper);

        QuestionByQuizIdRequestWrapper prefilledFirstQuestion = prefillQuestion("Radio",
                "question_content",
                "question_explanation",
                quiz.getId());
        Question firstQuestion = createQuestion(prefilledFirstQuestion);


        QuestionByQuizIdRequestWrapper prefilledSecondQuestion = prefillQuestion("Radio",
                "question_content",
                "question_explanation",
                quiz.getId());
        Question secondQuestion = createQuestion(prefilledSecondQuestion);

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Question>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Question>>() {
                }
        );

        List<Question> list = responseEntity.getBody();

        assertEquals(firstQuestion.getId(), list.get(0).getId());
        assertEquals(secondQuestion.getId(), list.get(1).getId());

        deleteGroupFromDB(group);
    }
}


