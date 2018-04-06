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
     * method for receive specify user by id
     *
     * @param id = uniq user id
     * @return specify user by id
     */
    User getUserById(long id);

    /**
     * method for get user
     *
     * @param userName = unic user name
     * @return specify user by naem
     */
    List<User> getUserByName(String userName);

    /**
     * method for receiving all users
     *
     * @return all users
     */
    List<User> getAllUsers();

    /**
     * method for user delete
     *
     * @param id = user's id for delete
     * @return removed user
     */
    User deleteUser(long id);
}
