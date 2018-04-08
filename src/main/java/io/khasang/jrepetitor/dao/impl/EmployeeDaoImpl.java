package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.EmployeeDao;
import io.khasang.jrepetitor.entity.Employee;

public class EmployeeDaoImpl extends BasicDaoImpl<Employee> implements EmployeeDao {
    public EmployeeDaoImpl(Class<Employee> entityClass) {
        super(entityClass);
    }
}
