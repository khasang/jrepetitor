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

    /**
     * method for receive specify cat by id
     *
     * @param id = uniq cat id
     * @return specify cat by id
     */
    Cat getCatById(long id);

    /**
     * method for cat delete
     *
     * @param id = cat's id for delete
     * @return removed cat
     */
    Cat deleteCat(long id);
    /**
     * method for update
     *
     * @param cat = cat for update
     * @return updated cat
     */
    Cat updateCat(Cat cat);
}
