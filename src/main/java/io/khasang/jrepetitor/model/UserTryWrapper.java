package io.khasang.jrepetitor.model;

import java.util.ArrayList;
import java.util.List;

public class UserTryWrapper {
    private Long quizId;

    private List<QuestionAnswerWrapper> questionAnswer;

    public UserTryWrapper() {
        questionAnswer = new ArrayList<>();
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public List<QuestionAnswerWrapper> getQuestionAnswerWrappers() {
        return questionAnswer;
    }

    public void setQuestionAnswerWrappers(List<QuestionAnswerWrapper> questionAnswerWrappers) {
        this.questionAnswer = questionAnswerWrappers;
    }

    public void addQuestionAnswer(QuestionAnswerWrapper questionAnswer) {
        this.questionAnswer.add(questionAnswer);
    }
}
