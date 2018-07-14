package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Group;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.model.wrappers.GroupWrapper;
import io.khasang.jrepetitor.model.wrappers.ItemByQuestionIdRequestWrapper;
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


public class ItemControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/item";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addItemAndCheck() {
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

        ItemByQuestionIdRequestWrapper itemByQuestionIdRequestWrapper = prefillItem(
                question.getId(),
                "item_content",
                (byte) 0);


        Item item = createItem(itemByQuestionIdRequestWrapper);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Item> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Item.class,
                item.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Item receivedItem = responseEntity.getBody();
        assertNotNull(receivedItem);

        deleteGroupFromDB(group);
    }

    @Test
    public void deleteItem() {
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

        ItemByQuestionIdRequestWrapper itemByQuestionIdRequestWrapper = prefillItem(
                question.getId(),
                "item_content",
                (byte) 0);


        Item item = createItem(itemByQuestionIdRequestWrapper);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Item> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Item.class,
                item.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        Item deletedItem = responseEntity.getBody();
        assertNotNull(deletedItem);

        ResponseEntity<Item> responseForDeleteItem = null;
        try {
            responseForDeleteItem = restTemplate.exchange(
                    ROOT + GET_BY_ID + "/{id}",
                    HttpMethod.GET,
                    null,
                    Item.class,
                    deletedItem.getId()
            );
        } catch (HttpClientErrorException e) {
            assertEquals(e.getMessage(), "404 null");
        }
        deleteGroupFromDB(group);
    }

    @Test
    public void getAllItems() {
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

        ItemByQuestionIdRequestWrapper firstItemByQuestionIdRequestWrapper = prefillItem(
                question.getId(),
                "first_item_content",
                (byte) 0);

        ItemByQuestionIdRequestWrapper secondItemByQuestionIdRequestWrapper = prefillItem(
                question.getId(),
                "second_item_content",
                (byte) 0);


        Item firstItem = createItem(firstItemByQuestionIdRequestWrapper);
        Item secondItem = createItem(secondItemByQuestionIdRequestWrapper);

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Item>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Item>>() {
                }
        );

        List<Item> list = responseEntity.getBody();

        assertEquals(firstItem.getId(), list.get(0).getId());
        assertEquals(secondItem.getId(), list.get(1).getId());
        deleteGroupFromDB(group);
    }

}

