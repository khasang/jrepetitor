package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.TutorDao;
import io.khasang.jrepetitor.entity.Tutor;

public class TutorDaoImpl extends BasicDaoImpl<Tutor> implements TutorDao {
    public TutorDaoImpl(Class<Tutor> entityClass) {
        super(entityClass);
    }
}