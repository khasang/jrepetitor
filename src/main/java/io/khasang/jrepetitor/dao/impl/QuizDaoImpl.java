package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.QuizDao;
import io.khasang.jrepetitor.entity.Quiz;

public class QuizDaoImpl extends BasicDaoImpl<Quiz> implements QuizDao {
    public QuizDaoImpl(Class<Quiz> entityClass) {
        super(entityClass);
    }
}
