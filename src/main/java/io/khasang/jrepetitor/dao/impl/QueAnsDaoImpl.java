package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.QueAnsDao;
import io.khasang.jrepetitor.entity.QueAns;

public class QueAnsDaoImpl extends BasicDaoImpl<QueAns> implements QueAnsDao{
    public QueAnsDaoImpl(Class<QueAns> entityClass){super(entityClass);}
}
