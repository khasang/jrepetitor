package io.khasang.jrepetitor.dao.impl;


import io.khasang.jrepetitor.dao.TeacherDao;
import io.khasang.jrepetitor.entity.Teacher;
import org.hibernate.Session;

import java.util.List;

public class TeacherDaoImpl extends BasicDaoImpl<Teacher> implements TeacherDao {
    public TeacherDaoImpl(Class<Teacher> entityClass) {
        super(entityClass);
    }
}
