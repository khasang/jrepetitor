package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.TeacherDao;
import io.khasang.jrepetitor.entity.Teacher;

public class TeacherDaoImpl extends BasicDaoImpl<Teacher> implements TeacherDao {
    public TeacherDaoImpl(Class<Teacher> entityClass) {
        super(entityClass);
    }
}
