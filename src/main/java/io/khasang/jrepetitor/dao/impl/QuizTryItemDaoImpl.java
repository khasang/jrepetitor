package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.QuizTryItemDao;
import io.khasang.jrepetitor.entity.QuizTryItem;

public class QuizTryItemDaoImpl extends BasicDaoImpl<QuizTryItem> implements QuizTryItemDao {
    public QuizTryItemDaoImpl(Class<QuizTryItem> entityClass) {
        super(entityClass);
    }

    @Override
    public QuizTryItem update(QuizTryItem quizTryItem) {
        super.getSessionFactory().update(quizTryItem);
        return quizTryItem;
    }


}