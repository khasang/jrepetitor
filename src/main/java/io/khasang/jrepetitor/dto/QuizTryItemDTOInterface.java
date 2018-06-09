package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.QuizTryItem;

import java.util.List;

public interface QuizTryItemDTOInterface {
    Long getId();

    void setId(Long id);

    byte getAnswerIsCorrect();

    void setAnswerIsCorrect(byte answerIsCorrect);

    QuestionDTOInterface getQuestion();

    void setQuestion(QuestionDTOInterface question);

    List<ItemDTOInterface> getItems();

    void setItems(List<ItemDTOInterface> items);

    QuizTryItemDTOInterface getQuizTryItem(QuizTryItem quizTryItem);

    List<QuizTryItemDTOInterface> getQuizTryItems(List<QuizTryItem> quizTryItems);
}
