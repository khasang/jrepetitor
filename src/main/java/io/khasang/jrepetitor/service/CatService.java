package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.Cat;

import java.util.List;

public interface CatService {

    /**
     *
     * method for receiving all cats for DB
     * @return all cats
     */
  List<Cat> getAllCats();

    /**
     * method for add cat
     * @param cat = new cat for creation in DB
     * @return created cat
     **/
   Cat addCat(Cat cat);

    /**
     * method for get cat by id
     * @param id = id cat for get
     * @return return cat by id
     */

    Cat getCatById(long id);

    /**method for delete cat by id
     *
     * @param id = cat's id delete
     * @return deleted cat
     */
    Cat deleteCat(long id);
}
