package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.CarDao;
import io.khasang.jrepetitor.entity.Car;

public class CarDaoImpl extends BasicDaoImpl<Car> implements CarDao {
    public CarDaoImpl(Class<Car> entityClass) {
        super(entityClass);
    }
}
