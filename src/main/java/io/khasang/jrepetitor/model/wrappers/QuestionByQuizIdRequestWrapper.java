package io.khasang.jrepetitor.model.wrappers;

public class QuestionByQuizIdRequestWrapper {

    private Long Id;

    private QuestionWrapper question;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public QuestionWrapper getQuestion() {
        return question;
    }

    public void setQuestion(QuestionWrapper question) {
        this.question = question;
    }
}
