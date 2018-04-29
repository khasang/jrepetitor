package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.UserTry;

import java.util.List;

public interface UserTryService {
    /**
     * method for add userTry
     *
     * @param userTry = new userTry for creation in DB
     * @return created userTry
     */
    UserTry addUserTry(UserTry userTry);

    /**
     * method for receiving all userTrys
     *
     * @return all userTrys
     */
    List<UserTry> getAllUserTrys();

    /**
     * method for receive specify userTry by id
     *
     * @param id = uniq userTry id
     * @return specify userTry by id
     */
    UserTry getUserTryById(long id);

    /**
     * method for userTry delete
     *
     * @param id = userTry's id for delete
     * @return removed userTry
     */
    UserTry deleteUserTry(long id);
}
