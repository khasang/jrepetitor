package io.khasang.jrepetitor.dto.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.khasang.jrepetitor.dto.ItemPreviewDTOInterface;
import io.khasang.jrepetitor.dto.QuestionDTOInterface;
import io.khasang.jrepetitor.dto.QuizPreviewDTOInterface;
import io.khasang.jrepetitor.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDTOImpl implements QuestionDTOInterface {

    @JsonIgnore
    @Autowired
    protected ItemPreviewDTOimpl itemPreviewDTO;

    @JsonIgnore
    @Autowired
    protected QuizPreviewDTOImpl quizPreviewDTO;

    private Long id;

    /**
     * question content
     */
    private String content;
    /**
     * question type: radio/CheckBox
     */
    private String type;

    /**
     * Answer variants
     */
    private List<ItemPreviewDTOInterface> items = new ArrayList<>();

    /**
     *
     */
    private String explanation;

    private QuizPreviewDTOInterface quiz;

    public QuestionDTOImpl() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public List<ItemPreviewDTOInterface> getItems() {
        return items;
    }

    @Override
    public void setItems(List<ItemPreviewDTOInterface> items) {
        this.items = items;
    }

    @Override
    public String getExplanation() {
        return explanation;
    }

    @Override
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public QuizPreviewDTOInterface getQuizPreview() {
        return quiz;
    }

    @Override
    public void setQuizPreview(QuizPreviewDTOInterface quizPreview) {
        this.quiz = quizPreview;
    }

    @Override
    public List<QuestionDTOInterface> getQuestionDTOList(List<Question> list) {
        List<QuestionDTOInterface> questionDTO = new ArrayList<>();
        if (list.isEmpty()) {
            return questionDTO;
        }
        for (Question question : list) {
            questionDTO.add(getQuestionDTO(question));
        }
        return questionDTO;
    }

    @Override
    public QuestionDTOInterface getQuestionDTO(Question question) {
        if (question == null) {
            return null;
        }
        QuestionDTOInterface questionDTO = new QuestionDTOImpl();
        questionDTO.setId(question.getId());
        questionDTO.setContent(question.getContent());
        questionDTO.setType(question.getType());
        questionDTO.setItems(itemPreviewDTO.getItemDTOList(question.getItems()));
        questionDTO.setQuizPreview(quizPreviewDTO.getPreviewDTO(question.getQuiz()));
        questionDTO.setExplanation(question.getExplanation());
        return questionDTO;
    }

}
