package io.khasang.jrepetitor.service;


import io.khasang.jrepetitor.entity.Users;


public interface UserService {

    /**
     * method for receive specify user by id
     *
     * @param id = uniq user id
     * @return specify user by id */


    Users getUserById(long id);

    Users getUserByName(String s);
}
