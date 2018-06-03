package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.QuestionDao;
import io.khasang.jrepetitor.entity.Question;

public class QuestionDaoImpl extends BasicDaoImpl<Question> implements QuestionDao {
    public QuestionDaoImpl(Class<Question> entityClass) {
        super(entityClass);
    }

    @Override
    public Question updateQuestion(Question question) {
        super.getSessionFactory().update(question);
        return question;
    }
}