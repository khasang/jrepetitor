package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.GroupDTOInterface;
import io.khasang.jrepetitor.dto.ItemDTOInterface;
import io.khasang.jrepetitor.dto.QuestionDTOInterface;
import io.khasang.jrepetitor.dto.QuizDTOInterface;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.entity.Quiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizDTO implements QuizDTOInterface {

    private static final Logger log = LoggerFactory.getLogger(QuizDTO.class);

    private Long id;

    private String name;

    private List<QuestionDTOInterface> questions = new ArrayList<>();

    private GroupDTOInterface group;

    /**
     * question level
     */
    private byte level;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<QuestionDTOInterface> getQuestions() {
        return questions;
    }

    @Override
    public void setQuestions(List<QuestionDTOInterface> questions) {
        this.questions = questions;
    }

    @Override
    public GroupDTOInterface getGroup() {
        return group;
    }

    @Override
    public void setGroup(GroupDTOInterface group) {
        this.group = group;
    }

    @Override
    public byte getLevel() {
        return level;
    }

    @Override
    public void setLevel(byte level) {
        this.level = level;
    }

    @Override
    public List<QuizDTOInterface> getQuizDTOList(List<Quiz> list) {
        List<QuizDTOInterface> quizDTOList = new ArrayList<>();

        try {
            for (Quiz quiz : list) {
                List<QuestionDTOInterface> questionDTOList = new ArrayList<>();

                QuizDTO quizDTO = new QuizDTO();
                quizDTO.setId(quiz.getId());
                quizDTO.setName(quiz.getName());
                quizDTO.setLevel(quiz.getLevel());

                nextQuest:
                for (Question question : quiz.getQuestions()) {
                    for (QuestionDTOInterface questionDTO : questionDTOList) {
                        if (questionDTO.getId() == question.getId()) {
                            continue nextQuest;
                        }
                    }

                    QuestionDTO questionDTO = new QuestionDTO();
                    questionDTO.setId(question.getId());
                    questionDTO.setContent(question.getContent());
                    questionDTO.setType(question.getType());
                    questionDTO.setExplanation(question.getExplanation());

                    List<ItemDTOInterface> itemDTOList = new ArrayList<>();

                    nextItem:
                    for (Item item : question.getItems()) {

                        for (ItemDTOInterface itemDTO : itemDTOList) {
                            if (itemDTO.getId() == item.getId())
                                continue nextItem;
                        }

                        ItemDTOImpl itemDTO = new ItemDTOImpl();
                        itemDTO.setId(item.getId());
                        itemDTO.setContent(item.getContent());
                        itemDTO.setCorrect(item.getCorrect());

                        itemDTOList.add(itemDTO);
                    }

                    questionDTO.setItems(itemDTOList);

                    questionDTOList.add(questionDTO);
                }

                quizDTO.setQuestions(questionDTOList);
                quizDTOList.add(quizDTO);

            }
        } catch (Exception e) {
            log.info("Quiz list is empty");
            log.info(e.getMessage());
        }
        return quizDTOList;

    }

    @Override
    public QuizDTOInterface getQuiz(Quiz quiz) {
        QuizDTO quizDTO = new QuizDTO();
        List<QuestionDTOInterface> questionDTOList = new ArrayList<>();

        try {
            quizDTO.setId(quiz.getId());
            quizDTO.setName(quiz.getName());
            quizDTO.setLevel(quiz.getLevel());

            nextQuest:
            for (Question question : quiz.getQuestions()) {
                //if questionDTOList contains quiestion with quiz_id=question.getQuiz().getId() - continue

                for (QuestionDTOInterface questionDTO : questionDTOList) {
                    if (questionDTO.getId() == question.getId()) {
                        continue nextQuest;
                    }
                }

                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setId(question.getId());
                questionDTO.setContent(question.getContent());
                questionDTO.setType(question.getType());
                questionDTO.setExplanation(question.getExplanation());

                List<ItemDTOInterface> itemDTOList = new ArrayList<>();

                nextItem:
                for (Item item : question.getItems()) {

                    for (ItemDTOInterface itemDTO : itemDTOList) {
                        if (itemDTO.getId() == item.getId())
                            continue nextItem;
                    }

                    ItemDTOImpl itemDTO = new ItemDTOImpl();
                    itemDTO.setId(item.getId());
                    itemDTO.setContent(item.getContent());
                    itemDTO.setCorrect(item.getCorrect());

                    itemDTOList.add(itemDTO);
                }

                questionDTO.setItems(itemDTOList);

                questionDTOList.add(questionDTO);
            }

            quizDTO.setQuestions(questionDTOList);
        } catch (Exception e) {
            log.info("Quiz is empty");
        }

        return quizDTO;
    }


}