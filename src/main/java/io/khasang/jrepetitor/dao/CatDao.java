package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.Cat;

import java.util.List;

public interface CatDao extends BasicDao<Cat> {
    /**
     * method for finding cats by name
     * @param name = cat's name for search
     * @return cats list with name - name
     */
    List<Cat> getCatsByName(String name);

}
