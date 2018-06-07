package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.*;
import io.khasang.jrepetitor.entity.QuizTry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class QuizTryDTOImpl implements QuizTryDTOInterface {
    private Long id;

    private UserDTOInterface user;

    private QuizDTOInterface quiz;

    private List<QuizTryItemDTOInterface> tryItemDTOList;

    private Date timestamp;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public UserDTOInterface getUser() {
        return user;
    }

    @Override
    public void setUser(UserDTOInterface user) {
        this.user = user;
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
    public List<QuizTryItemDTOInterface> getTryItemDTOList() {
        return tryItemDTOList;
    }

    @Override
    public void setTryItemDTOList(List<QuizTryItemDTOInterface> tryItemDTOList) {
        this.tryItemDTOList = tryItemDTOList;
    }

    @Override
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public QuizTryDTOInterface getQuizTryDTO(QuizTry quizTry) {
        QuizTryDTOInterface quizTryDTO = new QuizTryDTOImpl();
        quizTryDTO.setId(quizTry.getId());
        new QuizDTOImpl().getQuiz(quizTry.getQuiz());
        quizTryDTO.setQuiz(new QuizDTOImpl().getQuiz(quizTry.getQuiz()));
        quizTryDTO.setUser(new UserDTOImpl().getUserDTO(quizTry.getUser()));
        quizTryDTO.setTimestamp(quizTry.getTimestamp());
        quizTryDTO.setTryItemDTOList(new QuizTryItemDTOImpl().getQuizTryItems(quizTry.getTryItems()));
        return quizTryDTO;
    }

    public List<QuizTryDTOInterface> getQuizTryDTOList(List<QuizTry> quizTries) {
        List<QuizTryDTOInterface> quizTryDTOS = new ArrayList<>();
        if (quizTries.isEmpty()) {
            return quizTryDTOS;
        }
        for (QuizTry quizTry : quizTries) {
            quizTryDTOS.add(getQuizTryDTO(quizTry));
        }
        return quizTryDTOS;
    }
}
