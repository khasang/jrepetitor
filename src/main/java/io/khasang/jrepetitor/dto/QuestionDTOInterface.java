package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.dto.impl.ItemDTOImpl;
import io.khasang.jrepetitor.entity.Question;

import java.util.List;

public interface QuestionDTOInterface {
    Long getId();

    void setId(Long id);

    String getContent();

    void setContent(String content);

    String getType();

    void setType(String type);

    List<ItemDTOInterface> getItems();

    void setItems(List<ItemDTOInterface> items);

    QuizDTOInterface getQuiz();

    void setQuiz(QuizDTOInterface quiz);

    String getExplanation();

    void setExplanation(String explanation);

    List<QuestionDTOInterface> getQuestionDTOList(List<Question> list);

    QuestionDTOInterface getQuestionDTO(Question question);
}
