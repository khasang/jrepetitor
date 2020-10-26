package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.Group;

public interface GroupDao extends BasicDao<Group> {
    Group update(Group group);
}
