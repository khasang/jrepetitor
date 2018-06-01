package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.ItemDTOInterface;
import io.khasang.jrepetitor.dto.QuestionDTOInterface;
import io.khasang.jrepetitor.dto.QuizDTOInterface;
import io.khasang.jrepetitor.entity.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionDTOImpl implements QuestionDTOInterface {

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
    private List<ItemDTOInterface> items = new ArrayList<>();

    private QuizDTOInterface quiz;

    /**
     *
     */
    private String explanation;

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
    public List<ItemDTOInterface> getItems() {
        return items;
    }

    @Override
    public void setItems(List<ItemDTOInterface> items) {
        this.items = items;
    }

    @Override
    public QuizDTOInterface getQuiz() {
        return quiz;
    }

    @Override
    public void setQuiz(QuizDTOInterface quiz) {
        this.quiz = quiz;
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
        questionDTO.setItems(new ItemDTOImpl().getItemDTOList(question.getItems()));
        questionDTO.setExplanation(question.getExplanation());
        return questionDTO;
    }
}
