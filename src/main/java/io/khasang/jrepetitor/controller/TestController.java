package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dao.GroupDao;
import io.khasang.jrepetitor.dao.QuestionDao;
import io.khasang.jrepetitor.dao.QuizDao;
import io.khasang.jrepetitor.entity.Group;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.utils.ItemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    GroupDao groupDao;

    @Autowired
    ItemUtils itemUtils;


    @RequestMapping(value = "/createQuiz")
    public ResponseEntity<String> createTestQuiz() {
        Group group = new Group();
        group.setName("Java Core");
        groupDao.create(group);

        Quiz quiz1 = new Quiz();
        quiz1.setName("Java Basics");
        quiz1.setLevel((byte) 1);
        quiz1 = quizDao.create(quiz1);
        quiz1.setGroup(group);
        quizDao.update(quiz1);


        Question question1 = new Question();
        question1.setContent("Question1 content");
        question1.setExplanation("Question1 explanation");
        question1.setType("CheckBox");
        question1.setQuiz(quiz1);
        questionDao.create(question1);

        List<Item> itemsQuestion1 = new ArrayList<>();

        Item item1Question1 = itemUtils.createItem(
                (byte) 0,
                "Question1 Answer1"
        );
        itemsQuestion1.add(item1Question1);

        Item item2Question1 = itemUtils.createItem(
                (byte) 0,
                "Question1 Answer2"
        );
        itemsQuestion1.add(item2Question1);

        Item item3Question1 = itemUtils.createItem(
                (byte) 0,
                "Question1 Answer3"
        );
        itemsQuestion1.add(item3Question1);

        Item item4Question1 = itemUtils.createItem(
                (byte) 1,
                "Question1 Answer4 (correct)"
        );
        itemsQuestion1.add(item4Question1);

        itemUtils.addQuestion(itemsQuestion1, question1);
        question1.setItems(itemsQuestion1);

        questionDao.updateQuestion(question1);
        quizDao.update(quiz1);


        Question question2 = new Question();
        question2.setContent("Question2");
        question2.setExplanation("Question2 explanation");
        question2.setType("CheckBox");

        question2.setQuiz(quiz1);
        questionDao.create(question2);

        List<Item> itemsQuestion2 = new ArrayList<>();

        Item item1Question2 = itemUtils.createItem(
                (byte) 0,
                "Question2 Answer1"
        );
        itemsQuestion1.add(item1Question2);

        Item item2Question2 = itemUtils.createItem(
                (byte) 0,
                "Question2 Answer2"
        );

        itemsQuestion2.add(item2Question2);
        Item item3Question2 = itemUtils.createItem(
                (byte) 1,
                "Question2 Answer3 (correct)"
        );
        itemsQuestion2.add(item3Question2);

        Item item4Question2 = itemUtils.createItem(
                (byte) 0,
                "Question2 Answer4"
        );

        itemsQuestion2.add(item4Question2);

        itemUtils.addQuestion(itemsQuestion2, question2);
        question2.setItems(itemsQuestion2);
        questionDao.updateQuestion(question2);

        quizDao.update(quiz1);

        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }
}
