package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.Users;

public interface UserDao extends BasicDao<Users> {

    /**
     * method for finding user by name
     * @param name = user's name for search
     * @return user with name - name
     */
    Users getUserByName(String name);


}
