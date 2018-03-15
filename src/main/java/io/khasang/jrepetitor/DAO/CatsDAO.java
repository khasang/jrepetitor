package io.khasang.jrepetitor.DAO;

import io.khasang.jrepetitor.model.Cat;

/**
 * Created by DenisS on 15.03.2018.
 */
public interface CatsDAO {
    String insert(Cat cat);
    String update(Cat cat);
    String delete(Cat cat);
}
