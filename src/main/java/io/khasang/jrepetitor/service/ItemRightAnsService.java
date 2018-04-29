package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.ItemRightAns;

import java.util.List;

public interface ItemRightAnsService {

    /**
     * method for add right answers
     *
     * @param itemRightAns = new right answer for creation in DB
     * @return created right answer
     */
    ItemRightAns addItemRightAns(ItemRightAns itemRightAns);

    /**
     * method for get all right answers
     *
     * @return list created right answers
     */
    List<ItemRightAns> getAllItemRightAns();

    /**
     * method for get right answer by ID
     * @param id = id of answer we want to get
     * @return right answer
     */
    ItemRightAns getItemRightAnsById(long id);
}
