package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.Quiz;

public interface QuizDao extends BasicDao<Quiz> {
    Quiz update(Quiz quiz);
}
