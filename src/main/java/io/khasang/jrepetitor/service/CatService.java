package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.Cat;

import java.util.List;

public interface CatService {

    /**
     * adds cat
     *
     * @param cat = new cat for creation in DB
     * @return created cat
     */
    Cat addCat(Cat cat);

    /**
     * receiving all cats from DB
     *
     * @return all cats
     */
    List<Cat> getAllCats();
}
