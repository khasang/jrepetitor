package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.UserAnswerDao;
import io.khasang.jrepetitor.entity.UserAnswer;

public class UserAnswerDaoImpl extends BasicDaoImpl<UserAnswer> implements UserAnswerDao {
    public UserAnswerDaoImpl(Class<UserAnswer> entityClass) {
        super(entityClass);
    }
}
