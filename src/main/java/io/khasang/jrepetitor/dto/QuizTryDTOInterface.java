package io.khasang.jrepetitor.dto;

import java.util.Date;
import java.util.List;

public interface QuizTryDTOInterface {
    Long getId();

    void setId(Long id);

    UserDTOInterface getUser();

    void setUser(UserDTOInterface user);

    QuizDTOInterface getQuiz();

    void setQuiz(QuizDTOInterface quiz);

    List<QuizTryItemDTOInterface> getTryItemDTOList();

    void setTryItemDTOList(List<QuizTryItemDTOInterface> tryItemDTOList);

    Date getTimestamp();

    void setTimestamp(Date timestamp);
}
