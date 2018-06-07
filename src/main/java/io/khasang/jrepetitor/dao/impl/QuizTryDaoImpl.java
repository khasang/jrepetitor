package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.QuizTryDao;
import io.khasang.jrepetitor.entity.QuizTry;


public class QuizTryDaoImpl extends BasicDaoImpl<QuizTry> implements QuizTryDao {

    public QuizTryDaoImpl(Class<QuizTry> entityClass) {
        super(entityClass);
    }

    @Override
    public QuizTry update(QuizTry quizTry) {
        super.getSessionFactory().update(quizTry);
        return quizTry;
    }

}
