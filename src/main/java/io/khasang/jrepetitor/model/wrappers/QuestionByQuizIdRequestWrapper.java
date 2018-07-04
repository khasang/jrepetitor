package io.khasang.jrepetitor.model.wrappers;

import io.khasang.jrepetitor.entity.Question;

public class QuestionByQuizIdRequestWrapper {

    private Long Id;

    private Question question;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
