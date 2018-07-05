package io.khasang.jrepetitor.model.wrappers;

import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.entity.Quiz;

import java.util.List;

public class QuizWrapper {
    private String name;
    private List<QuestionWrapper> questions;
    private byte level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionWrapper> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionWrapper> questions) {
        this.questions = questions;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public Quiz getQuiz() {
        Quiz quiz = new Quiz();
        quiz.setName(name);
        quiz.setLevel(level);
        if (questions == null || questions.isEmpty()) {
            return quiz;
        }
        for (QuestionWrapper question : questions) {
            Question currentQuestion = question.getQuestion();
            currentQuestion.setQuiz(quiz);
            quiz.addQuestion(currentQuestion);
        }
        return quiz;
    }

}
