package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.User;

public interface UserDao extends BasicDao<User> {
    /**
     * method for finding user by name
     *
     * @param name = user name for search
     * @return user with name - name
     */
    User getUserByLogin(String name);

    /**
     * method for update user
     *
     * @param user = user name
     * @return user updated user
     */
    User updateUser(User user);


}
