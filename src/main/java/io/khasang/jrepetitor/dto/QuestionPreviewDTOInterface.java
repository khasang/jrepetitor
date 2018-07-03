package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Question;

import java.util.List;

public interface QuestionPreviewDTOInterface {
    Long getId();

    void setId(Long id);

    String getContent();

    void setContent(String content);

    QuestionPreviewDTOInterface getQuestion(Question question);

    List<QuestionPreviewDTOInterface> getQuestions(List<Question> questions);
}
