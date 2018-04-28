package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.CatDao;
import io.khasang.jrepetitor.entity.Cat;

public class CatDaoImpl extends BasicDaoImpl<Cat> implements CatDao {
    /**
     * affa
     */
    public CatDaoImpl(Class<Cat> entityClass) {
        super(entityClass);
    }
}
