package io.khasang.jrepetitor.DAO.impl;

import io.khasang.jrepetitor.DAO.BasicDao;
import io.khasang.jrepetitor.DAO.KotDao;
import io.khasang.jrepetitor.entity.Kot;

public class KotDaoImpl<Kot> extends BasicDaoImpl implements KotDao {
    public KotDaoImpl(Class<Kot> entityClass) {
        super(entityClass);
    }


}
