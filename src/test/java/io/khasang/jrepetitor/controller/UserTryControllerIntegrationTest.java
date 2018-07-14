package io.khasang.jrepetitor.controller;


import io.khasang.jrepetitor.entity.*;
import io.khasang.jrepetitor.model.wrappers.QuestionAnswerWrapper;
import io.khasang.jrepetitor.model.wrappers.QuestionWrapper;
import io.khasang.jrepetitor.model.wrappers.SelectedItemWrapper;
import io.khasang.jrepetitor.model.wrappers.UserTryWrapper;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static io.khasang.jrepetitor.util.TestUtils.*;

public class UserTryControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080";
    private static final String USER_CONTROLLER = "/users";
    private static final String ADD = "/add";
    private static final String TRY_QUIZ = "/try_quiz";
    private static final String DELETE = "/delete";
    private static final String ALL = "/all";

    @Test
    public void addQuizTry() {
        Quiz quiz = createTestQuiz();

        User user = createUser("test",
                "test",
                "test",
                "test",
                "test",
                "test@domain.zone",
                "1234567890",
                "ROLE_USER",
                ROOT + USER_CONTROLLER,
                ADD);

        QuizTry rightAnswer = rightAnswer("test", "test", quiz, 0);
        assertEquals(rightAnswer.getQuiz().getId(), quiz.getId());
        assertEquals(rightAnswer.getIncorrectAnswerCount(), 0);
        assertEquals(rightAnswer.getRightAnswerCount(), 4);
        assertEquals(rightAnswer.getQuestionsCount(), 4);

        List<QuizTryItem> quizTryItems = rightAnswer.getTryItems();
        for (QuizTryItem quizTryItem : quizTryItems) {
            assertEquals(quizTryItem.getAnswerIsCorrect(), 1);
        }

        QuizTry incorrectAnswer = rightAnswer("test", "test", quiz, 1);
        assertEquals(incorrectAnswer.getQuiz().getId(), quiz.getId());
        assertEquals(incorrectAnswer.getIncorrectAnswerCount(), 4);
        assertEquals(incorrectAnswer.getRightAnswerCount(), 0);
        assertEquals(incorrectAnswer.getQuestionsCount(), 4);

        List<QuizTryItem> inCorrectQuizTryItems = rightAnswer.getTryItems();
        for (QuizTryItem quizTryItem : inCorrectQuizTryItems) {
            assertEquals(quizTryItem.getAnswerIsCorrect(), 0);
        }

        removeTryQuiz(rightAnswer.getId(), "test", "test");
        removeTryQuiz(incorrectAnswer.getId(), "test", "test");

        deleteGroupFromDB(quiz.getGroup());
        removeUser(user.getId());
    }

    @Test
    public void allQuizTry() {
        Quiz quiz = createTestQuiz();

        User user = createUser("test",
                "test",
                "test",
                "test",
                "test",
                "test@domain.zone",
                "1234567890",
                "ROLE_USER",
                ROOT + USER_CONTROLLER,
                ADD);

        List<QuizTry> quizTries = new ArrayList<>();
        quizTries.add(rightAnswer("test", "test", quiz, 0));
        quizTries.add(rightAnswer("test", "test", quiz, 0));
        quizTries.add(rightAnswer("test", "test", quiz, 0));

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<QuizTry>> responseEntity = template.exchange(
                ROOT + TRY_QUIZ + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<QuizTry>>() {
                }
        );

        List<QuizTry> list = responseEntity.getBody();
        assertEquals(3, list.size());
        checkTriesList(list);
        for (QuizTry quizTry : quizTries) {
            removeTryQuiz(quizTry.getId(), "test", "test");
        }
        deleteGroupFromDB(quiz.getGroup());
        removeUser(user.getId());
    }

    @Test
    public void getByIdQuizTry() {
        Quiz quiz = createTestQuiz();

        User user = createUser("test",
                "test",
                "test",
                "test",
                "test",
                "test@domain.zone",
                "1234567890",
                "ROLE_USER",
                ROOT + USER_CONTROLLER,
                ADD);

        QuizTry quizTry = rightAnswer("test", "test", quiz, 0);
        String sessionId = formAuth("test", "test");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        ResponseEntity<QuizTry> responseEntityGetById = restTemplate.exchange(
                ROOT + "/try_quiz/get" + "/{id}",
                HttpMethod.GET,
                requestEntity,
                QuizTry.class,
                quizTry.getId()
        );

        QuizTry correctTry = responseEntityGetById.getBody();
        assertEquals(correctTry.getId(), quizTry.getId());
        removeTryQuiz(correctTry.getId(), "test", "test");
        deleteGroupFromDB(quiz.getGroup());
        removeUser(user.getId());
    }

    @Test
    public void deleteQuizTry() {
        Quiz quiz = createTestQuiz();

        User user = createUser("test",
                "test",
                "test",
                "test",
                "test",
                "test@domain.zone",
                "1234567890",
                "ROLE_USER",
                ROOT + USER_CONTROLLER,
                ADD);

        QuizTry quizTry = rightAnswer("test", "test", quiz, 0);
        removeTryQuiz(quizTry.getId(), "test", "test");
        deleteGroupFromDB(quiz.getGroup());
        removeUser(user.getId());
    }

    @Test
    public void getMyTriesQuizTry() {
        Quiz quiz = createTestQuiz();

        User user = createUser("test",
                "test",
                "test",
                "test",
                "test",
                "test@domain.zone",
                "1234567890",
                "ROLE_USER",
                ROOT + USER_CONTROLLER,
                ADD);

        List<QuizTry> quizTries = new ArrayList<>();
        quizTries.add(rightAnswer("test", "test", quiz, 0));
        quizTries.add(rightAnswer("test", "test", quiz, 0));
        quizTries.add(rightAnswer("test", "test", quiz, 0));

        String sessionId = formAuth("test", "test");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<QuizTry>> responseEntity = template.exchange(
                ROOT + TRY_QUIZ + "/get_my_tries",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<QuizTry>>() {
                }
        );
        List<QuizTry> list = responseEntity.getBody();
        assertEquals(3, list.size());
        checkTriesList(list);
        for (QuizTry quizTry : quizTries) {
            removeTryQuiz(quizTry.getId(), "test", "test");
        }
        deleteGroupFromDB(quiz.getGroup());
        removeUser(user.getId());

    }

    private QuizTry rightAnswer(String login, String password, Quiz quiz, int selectedItemNumber) {
        UserTryWrapper userTryWrapper = new UserTryWrapper();
        userTryWrapper.setQuizId(quiz.getId());

        List<Question> questions = quiz.getQuestions();

        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Question question : questions) {
            QuestionAnswerWrapper questionAnswerWrapper = new QuestionAnswerWrapper();
            questionAnswerWrapper.setQuestionId(question.getId());
            SelectedItemWrapper selectedItemWrapper = new SelectedItemWrapper();
            selectedItemWrapper.setSelectedItemId(question.getItems().get(selectedItemNumber).getId());
            questionAnswerWrapper.addSelectedItem(selectedItemWrapper);
            userTryWrapper.addQuestionAnswer(questionAnswerWrapper);
        }

        RestTemplate restTemplate = new RestTemplate();
        String sessionId = formAuth(login, password);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(userTryWrapper, requestHeaders);
        ResponseEntity<QuizTry> responseEntity = restTemplate.exchange(
                ROOT + TRY_QUIZ + ADD,
                HttpMethod.POST,
                requestEntity,
                QuizTry.class
        );

        return responseEntity.getBody();
    }


    private QuizTry removeTryQuiz(Long id, String adminName, String adminPassword) {
        String sessionId = formAuth(adminName, adminPassword);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
        ResponseEntity<QuizTry> responseEntity = restTemplate.exchange(
                ROOT + TRY_QUIZ + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                requestEntity,
                QuizTry.class,
                id
        );
        assertEquals(responseEntity.getStatusCodeValue(), 200);
        return responseEntity.getBody();
    }

    private void checkTriesList(List<QuizTry> quizTries) {
        for (QuizTry quizTry : quizTries) {
            assertEquals(quizTry.getUser().getLogin(), "test");
            assertEquals(quizTry.getRightAnswerCount(), 4);
            assertEquals(quizTry.getIncorrectAnswerCount(), 0);
            assertEquals(quizTry.getQuestionsCount(), 4);
        }
    }

}
