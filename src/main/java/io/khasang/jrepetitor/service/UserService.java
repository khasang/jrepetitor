package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.User;

import java.util.List;

public interface UserService {
    /**
     * method for add user
     *
     * @param user = new user for creation in DB
     * @return created user
     */
    User addUser(User user);

    /**
     * method for receiving all users
     *
     * @return all users
     */
    List<User> getAllUsers();


}
