package io.khasang.jrepetitor.model.wrappers;

public class QuizByGroupIdRequestWrapper {
    private Long Id;

    private QuizWrapper quiz;

    public QuizByGroupIdRequestWrapper() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public QuizWrapper getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizWrapper quiz) {
        this.quiz = quiz;
    }
}
