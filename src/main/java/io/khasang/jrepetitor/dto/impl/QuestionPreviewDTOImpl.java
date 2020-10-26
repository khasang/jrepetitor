package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.QuestionPreviewDTOInterface;
import io.khasang.jrepetitor.entity.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionPreviewDTOImpl implements QuestionPreviewDTOInterface {
    private Long id;

    private String content;

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
    public QuestionPreviewDTOInterface getQuestion(Question question) {
        QuestionPreviewDTOInterface questionPreviewDTOInterface = new QuestionPreviewDTOImpl();
        questionPreviewDTOInterface.setContent(question.getContent());
        questionPreviewDTOInterface.setId(question.getId());
        return questionPreviewDTOInterface;
    }

    @Override
    public List<QuestionPreviewDTOInterface> getQuestions(List<Question> questions) {
        List<QuestionPreviewDTOInterface> questionPreviewDTOs = new ArrayList<>();
        if (questions == null) {
            return questionPreviewDTOs;
        }
        for (Question question : questions) {
            questionPreviewDTOs.add(getQuestion(question));
        }
        return questionPreviewDTOs;
    }

}
