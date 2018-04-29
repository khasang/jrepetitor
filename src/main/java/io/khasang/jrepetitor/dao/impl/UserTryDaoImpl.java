package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.UserTryDao;
import io.khasang.jrepetitor.entity.UserTry;

public class UserTryDaoImpl extends BasicDaoImpl<UserTry> implements UserTryDao {
    public UserTryDaoImpl(Class<UserTry> entityClass) {
        super(entityClass);
    }
}
