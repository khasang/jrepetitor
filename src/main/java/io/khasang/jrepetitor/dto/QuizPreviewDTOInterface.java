package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Quiz;

import java.util.List;

public interface QuizPreviewDTOInterface {

    Long getId();

    String getName();

    Byte getLevel();

    void setId(Long id);

    void setName(String name);

    void setLevel(Byte level);

    List<QuizPreviewDTOInterface> getListQuizPreviewDTO(List<Quiz> quizzes);

    QuizPreviewDTOInterface getPreviewDTO(Quiz quiz);
}
