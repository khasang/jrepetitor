package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.QuizTry;

import java.util.Date;
import java.util.List;

public interface QuizTryDTOInterface {
    Long getId();

    void setId(Long id);

    UserDTOInterface getUser();

    void setUser(UserDTOInterface user);

    QuizPreviewDTOInterface getQuiz();

    void setQuiz(QuizPreviewDTOInterface quiz);

    List<QuizTryItemDTOInterface> getTryItemDTOList();

    void setTryItemDTOList(List<QuizTryItemDTOInterface> tryItemDTOList);

    Date getTimestamp();

    void setTimestamp(Date timestamp);

    int getQuestionsCount();

    void setQuestionsCount(int questionsCount);

    int getRightAnswerCount();

    void setRightAnswerCount(int rightAnswerCount);

    int getIncorrectAnswerCount();

    void setIncorrectAnswerCount(int incorrectAnswerCount);

    QuizTryDTOInterface getQuizTryDTO(QuizTry quizTry);

    List<QuizTryDTOInterface> getQuizTryDTOList(List<QuizTry> quizTries);
}


