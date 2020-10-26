package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.GroupDao;
import io.khasang.jrepetitor.entity.Group;
import org.springframework.stereotype.Repository;

public class GroupDaoImpl extends BasicDaoImpl<Group> implements GroupDao {
    public GroupDaoImpl(Class<Group> entityClass) {
        super(entityClass);
    }

    @Override
    public Group update(Group group) {
        super.getSessionFactory().update(group);
        return group;
    }
}