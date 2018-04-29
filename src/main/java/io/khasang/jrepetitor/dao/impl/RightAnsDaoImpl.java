package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.RightAnsDao;
import io.khasang.jrepetitor.entity.RightAns;

public class RightAnsDaoImpl extends BasicDaoImpl<RightAns> implements RightAnsDao {
    public RightAnsDaoImpl(Class<RightAns> entityClass) {
        super(entityClass);
    }
}
