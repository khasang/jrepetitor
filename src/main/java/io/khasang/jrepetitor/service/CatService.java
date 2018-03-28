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

    /**
     * updating cat in DB
     *
     * @param cat = new cat which exchange old one
     * @return new cat
     */
    Cat updateCat(Cat cat);

    /**
     * receive specify cat by id
     *
     * @param id = uniq cat id
     * @return specify cat by id
     */
    Cat getCatById(long id);

    /**
     * delete specify cat by id
     *
     * @param id = cat's id for delete
     * @return deleted cat
     */
    Cat deleteCat(long id);
}
