package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Quiz;

import java.util.List;

public interface QuizDTOInterface {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    List<QuestionDTO> getQuestions();

    void setQuestions(List<QuestionDTO> questions);

    GroupDTO getGroup();

    void setGroup(GroupDTO group);

    byte getLevel();

    void setLevel(byte level);

    List<QuizDTOInterface> getQuizDTOList(List<Quiz> list);

    QuizDTOInterface getQuiz(Quiz quiz);

}