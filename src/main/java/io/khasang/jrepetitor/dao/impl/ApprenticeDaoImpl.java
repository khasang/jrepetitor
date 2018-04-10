package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.ApprenticeDao;
import io.khasang.jrepetitor.entity.Apprentice;

public class ApprenticeDaoImpl extends BasicDaoImpl<Apprentice>  implements ApprenticeDao {
    public ApprenticeDaoImpl(Class<Apprentice> entityClass) {
        super(entityClass);
    }
}
