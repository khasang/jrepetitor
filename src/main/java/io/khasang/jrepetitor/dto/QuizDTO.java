package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.entity.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizDTO {

    private Long id;

    private String name;

    private List<QuestionDTO> questions = new ArrayList<QuestionDTO>();

    private GroupDTO group;

    private byte level; // question level

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public List<QuizDTO> getQuizDTOList(List<Quiz> list) {
        List<QuizDTO> quizDTOList = new ArrayList<>();

        try {
            for (Quiz quiz : list) {
                List<QuestionDTO> questionDTOList = new ArrayList<>();

                QuizDTO quizDTO = new QuizDTO();
                quizDTO.setId(quiz.getId());
                quizDTO.setName(quiz.getName());
                quizDTO.setLevel(quiz.getLevel());

                nextQuest:
                for (Question question : quiz.getQuestions()) {
                    for (QuestionDTO questionDTO : questionDTOList) {
                        if (questionDTO.getId() == question.getId()) {
                            continue nextQuest;
                        }
                    }

                    QuestionDTO questionDTO = new QuestionDTO();
                    questionDTO.setId(question.getId());
                    questionDTO.setContent(question.getContent());
                    questionDTO.setType(question.getType());
                    questionDTO.setExplanation(question.getExplanation());

                    List<ItemDTO> itemDTOList = new ArrayList<>();

                    nextItem:
                    for (Item item : question.getItems()) {

                        for (ItemDTO itemDTO : itemDTOList) {
                            if (itemDTO.getId() == item.getId())
                                continue nextItem;
                        }

                        ItemDTO itemDTO = new ItemDTO();
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
        }
        catch (Exception e){}
        return quizDTOList;

    }

    public QuizDTO getQuiz(Quiz quiz) {
        QuizDTO quizDTO = new QuizDTO();
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        try {
            quizDTO.setId(quiz.getId());
            quizDTO.setName(quiz.getName());
            quizDTO.setLevel(quiz.getLevel());

            nextQuest:
            for (Question question : quiz.getQuestions()) {
                //if questionDTOList contains quiestion with quiz_id=question.getQuiz().getId() - continue

                for (QuestionDTO questionDTO : questionDTOList) {
                    if (questionDTO.getId() == question.getId()) {
                        continue nextQuest;
                    }
                }

                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setId(question.getId());
                questionDTO.setContent(question.getContent());
                questionDTO.setType(question.getType());
                questionDTO.setExplanation(question.getExplanation());

                List<ItemDTO> itemDTOList = new ArrayList<>();

                nextItem:
                for (Item item : question.getItems()) {

                    for (ItemDTO itemDTO : itemDTOList) {
                        if (itemDTO.getId() == item.getId())
                            continue nextItem;
                    }

                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setId(item.getId());
                    itemDTO.setContent(item.getContent());
                    itemDTO.setCorrect(item.getCorrect());

                    itemDTOList.add(itemDTO);
                }

                questionDTO.setItems(itemDTOList);

                questionDTOList.add(questionDTO);
            }

            quizDTO.setQuestions(questionDTOList);
        }
         catch (Exception e){}

        return quizDTO;
    }
}