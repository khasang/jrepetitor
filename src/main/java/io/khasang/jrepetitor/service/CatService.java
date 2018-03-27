package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.Cat;

import java.util.List;

public interface CatService {
    /**
     * method for add cat
     *
     * @param cat = new cat for creation in DB
     * @return created cat
     */
    Cat addCat(Cat cat);

    /**
     * method for receiving all cats
     *
     * @return all cats
     */
    List<Cat> getAllCats();
}
