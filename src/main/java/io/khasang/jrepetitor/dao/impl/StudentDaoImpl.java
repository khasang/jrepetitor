package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.StudentDao;
import io.khasang.jrepetitor.entity.Student;

public class StudentDaoImpl extends BasicDaoImpl<Student> implements StudentDao {
    public StudentDaoImpl(Class<Student> entityClass) {
        super(entityClass);
    }
}
