package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.Dog;

import java.util.List;

public interface DogsService {
    void insert(Dog dog);
    void update(Dog dog);
    void delete(long dog_id);
    Dog getDog(long dog_id);
    List<Dog> findAll();
}
