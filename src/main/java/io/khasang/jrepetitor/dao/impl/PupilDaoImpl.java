package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.PupilDao;
import io.khasang.jrepetitor.entity.Pupil;

public class PupilDaoImpl extends BasicDaoImpl<Pupil> implements PupilDao {
    public PupilDaoImpl(Class<Pupil> entityClass) {
        super(entityClass);
    }
}
