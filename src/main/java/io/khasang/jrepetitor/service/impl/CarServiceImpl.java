package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.entity.Car;
import io.khasang.jrepetitor.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService{
    @Override
    public List<Car> getAllCars() {
        return null;
    }
}
