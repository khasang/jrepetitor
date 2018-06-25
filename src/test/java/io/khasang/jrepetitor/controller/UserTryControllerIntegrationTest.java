package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.entity.QuizTry;
import io.khasang.jrepetitor.entity.QuizTryItem;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.model.QuestionAnswerWrapper;
import io.khasang.jrepetitor.model.SelectedItemWrapper;
import io.khasang.jrepetitor.model.UserTryWrapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static io.khasang.jrepetitor.util.TestUtils.*;
import static org.junit.Assert.*;

public class UserTryControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080";
    private static final String USER_CONTROLLER = "/users";
    private static final String ADD = "/add";
    private static final String TRY_QUIZ = "/try_quiz";
    private static final String ALL = "/all";

    @Before
    public void setUp() {
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

        User admin = createUser("admin",
                "admin_name",
                "admin",
                "admin_middle_name",
                "admin_surname",
                "admin_email",
                "admin_phone",
                "ROLE_ADMIN",
                ROOT + USER_CONTROLLER,
                ADD);

        createTestQuiz();
    }

    @Test
    public void addQuizTry() {
        ResponseEntity rightAnswerEntity = rightAnswer("test", "test");

        assertEquals(rightAnswerEntity.getStatusCodeValue(), 200);
        QuizTry rightAnswer = (QuizTry) rightAnswerEntity.getBody();
        assertEquals(rightAnswer.getQuiz().getId(), new Long(1));
        assertEquals(rightAnswer.getIncorrectAnswerCount(), 0);
        assertEquals(rightAnswer.getRightAnswerCount(), 4);
        assertEquals(rightAnswer.getQuestionsCount(), 4);

        List<QuizTryItem> quizTryItems = rightAnswer.getTryItems();
        for (QuizTryItem quizTryItem : quizTryItems) {
            assertEquals(quizTryItem.getAnswerIsCorrect(), 1);
        }

        ResponseEntity incorrectAnswerEntity = incorrectAnswer("test", "test");

        assertEquals(incorrectAnswerEntity.getStatusCodeValue(), 200);
        QuizTry incorrectAnswer = (QuizTry) incorrectAnswerEntity.getBody();
        assertEquals(incorrectAnswer.getQuiz().getId(), new Long(1));
        assertEquals(incorrectAnswer.getIncorrectAnswerCount(), 4);
        assertEquals(incorrectAnswer.getRightAnswerCount(), 0);
        assertEquals(incorrectAnswer.getQuestionsCount(), 4);

        List<QuizTryItem> inCorrectQuizTryItems = rightAnswer.getTryItems();
        for (QuizTryItem quizTryItem : inCorrectQuizTryItems) {
            assertEquals(quizTryItem.getAnswerIsCorrect(), 0);
        }

        removeTryQuiz(1L, "test", "test");
        removeTryQuiz(2L, "test", "test");
    }

    @Test
    public void allQuizTry() {
        rightAnswer("test", "test");
        rightAnswer("test", "test");
        rightAnswer("test", "test");

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<QuizTry>> responseEntity = template.exchange(
                ROOT + TRY_QUIZ + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<QuizTry>>() {
                }
        );

        List<QuizTry> list = responseEntity.getBody();
        assertEquals(list.size(), 3);
        for (QuizTry quizTry : list) {
            assertEquals(quizTry.getUser().getLogin(), "test");
            assertEquals(quizTry.getRightAnswerCount(), 4);
            assertEquals(quizTry.getIncorrectAnswerCount(), 0);
            assertEquals(quizTry.getQuestionsCount(), 4);
        }
    }

    @Test
    public void getByIdQuizTry() {
        ResponseEntity responseEntity = rightAnswer("test", "test");
        QuizTry rightQuizTry = (QuizTry) responseEntity.getBody();
        ResponseEntity responseEntity1 = rightAnswer("test", "test");
        QuizTry incorrectQuizTry = (QuizTry) responseEntity1.getBody();

        String sessionId = formAuth("test", "test");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        ResponseEntity responseEntityGetById = restTemplate.exchange(
                ROOT + "/quiz/delete/" + "?id=" + "{id}",
                HttpMethod.DELETE,
                requestEntity,
                Quiz.class,
                rightQuizTry.getId()
        );

        System.out.println("e");
    }

    @Test
    public void deleteQuizTry() {

    }

    @Test
    public void getMyTriesQuizTry() {

    }

    private ResponseEntity rightAnswer(String login, String password) {
        UserTryWrapper userTryWrapper = new UserTryWrapper();
        userTryWrapper.setQuizId(1L);

        QuestionAnswerWrapper questionAnswerWrapper1 = new QuestionAnswerWrapper();
        questionAnswerWrapper1.setQuestionId(1L);
        SelectedItemWrapper selectedItemWrapper1 = new SelectedItemWrapper();
        selectedItemWrapper1.setSelectedItemId(1L);
        questionAnswerWrapper1.addSelectedItem(selectedItemWrapper1);

        QuestionAnswerWrapper questionAnswerWrapper2 = new QuestionAnswerWrapper();
        questionAnswerWrapper2.setQuestionId(2L);
        SelectedItemWrapper selectedItemWrapper2 = new SelectedItemWrapper();
        selectedItemWrapper2.setSelectedItemId(5L);
        questionAnswerWrapper2.addSelectedItem(selectedItemWrapper2);

        QuestionAnswerWrapper questionAnswerWrapper3 = new QuestionAnswerWrapper();
        questionAnswerWrapper3.setQuestionId(3L);
        SelectedItemWrapper selectedItemWrapper3 = new SelectedItemWrapper();
        selectedItemWrapper3.setSelectedItemId(9L);
        questionAnswerWrapper3.addSelectedItem(selectedItemWrapper3);

        QuestionAnswerWrapper questionAnswerWrapper4 = new QuestionAnswerWrapper();
        questionAnswerWrapper4.setQuestionId(4L);
        SelectedItemWrapper selectedItemWrapper4 = new SelectedItemWrapper();
        selectedItemWrapper4.setSelectedItemId(13L);
        questionAnswerWrapper4.addSelectedItem(selectedItemWrapper4);

        userTryWrapper.addQuestionAnswer(questionAnswerWrapper1);
        userTryWrapper.addQuestionAnswer(questionAnswerWrapper2);
        userTryWrapper.addQuestionAnswer(questionAnswerWrapper3);
        userTryWrapper.addQuestionAnswer(questionAnswerWrapper4);

        RestTemplate restTemplate = new RestTemplate();
        String sessionId = formAuth(login, password);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(userTryWrapper, requestHeaders);
        ResponseEntity responseEntity = restTemplate.exchange(
                ROOT + TRY_QUIZ + ADD,
                HttpMethod.POST,
                requestEntity,
                QuizTry.class
        );

        return responseEntity;
    }

    private ResponseEntity incorrectAnswer(String login, String password) {
        UserTryWrapper userTryWrapper = new UserTryWrapper();
        userTryWrapper.setQuizId(1L);

        QuestionAnswerWrapper questionAnswerWrapper1 = new QuestionAnswerWrapper();
        questionAnswerWrapper1.setQuestionId(1L);
        SelectedItemWrapper selectedItemWrapper1 = new SelectedItemWrapper();
        selectedItemWrapper1.setSelectedItemId(2L);
        questionAnswerWrapper1.addSelectedItem(selectedItemWrapper1);

        QuestionAnswerWrapper questionAnswerWrapper2 = new QuestionAnswerWrapper();
        questionAnswerWrapper2.setQuestionId(2L);
        SelectedItemWrapper selectedItemWrapper2 = new SelectedItemWrapper();
        selectedItemWrapper2.setSelectedItemId(6L);
        questionAnswerWrapper2.addSelectedItem(selectedItemWrapper2);

        QuestionAnswerWrapper questionAnswerWrapper3 = new QuestionAnswerWrapper();
        questionAnswerWrapper3.setQuestionId(3L);
        SelectedItemWrapper selectedItemWrapper3 = new SelectedItemWrapper();
        selectedItemWrapper3.setSelectedItemId(10L);
        questionAnswerWrapper3.addSelectedItem(selectedItemWrapper3);

        QuestionAnswerWrapper questionAnswerWrapper4 = new QuestionAnswerWrapper();
        questionAnswerWrapper4.setQuestionId(4L);
        SelectedItemWrapper selectedItemWrapper4 = new SelectedItemWrapper();
        selectedItemWrapper4.setSelectedItemId(14L);
        questionAnswerWrapper4.addSelectedItem(selectedItemWrapper4);

        userTryWrapper.addQuestionAnswer(questionAnswerWrapper1);
        userTryWrapper.addQuestionAnswer(questionAnswerWrapper2);
        userTryWrapper.addQuestionAnswer(questionAnswerWrapper3);
        userTryWrapper.addQuestionAnswer(questionAnswerWrapper4);

        RestTemplate restTemplate = new RestTemplate();
        String sessionId = formAuth(login, password);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(userTryWrapper, requestHeaders);
        ResponseEntity responseEntity = restTemplate.exchange(
                ROOT + TRY_QUIZ + ADD,
                HttpMethod.POST,
                requestEntity,
                QuizTry.class
        );

        return responseEntity;
    }

    private QuizTry removeTryQuiz(Long id, String adminName, String adminPassword) {
        String sessionId = formAuth(adminName, adminPassword);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
        ResponseEntity responseEntity = restTemplate.exchange(
                ROOT + "/try_quiz/delete/" + "?id=" + "{id}",
                HttpMethod.DELETE,
                requestEntity,
                QuizTry.class,
                id
        );
        return (QuizTry) responseEntity.getBody();
    }
}
