package io.khasang.jrepetitor.DAO;

import io.khasang.jrepetitor.entity.Dog;

import java.util.List;

/**
 * Created by DenisS on 26.03.2018.
 */
public interface DogsDAO {
    void insert(Dog dog);
    void update(Dog dog);
    void delete(long dog_id);
    Dog getDog(long dog_id);
    List<Dog> findAll();
}
