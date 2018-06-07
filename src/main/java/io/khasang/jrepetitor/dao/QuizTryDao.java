package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.QuizTry;

public interface QuizTryDao extends BasicDao<QuizTry> {
    QuizTry update(QuizTry quizTry);
}
