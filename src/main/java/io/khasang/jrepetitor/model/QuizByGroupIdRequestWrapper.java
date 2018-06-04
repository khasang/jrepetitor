package io.khasang.jrepetitor.model;

import io.khasang.jrepetitor.entity.Quiz;

public class QuizByGroupIdRequestWrapper {
    private Long Id;

    private Quiz quiz;

    public QuizByGroupIdRequestWrapper() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
