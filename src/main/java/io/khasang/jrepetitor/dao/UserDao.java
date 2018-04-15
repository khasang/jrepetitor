package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.Users;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserDao extends BasicDao<Users> {

    /**
     * method for finding cats by name
     * @param name = cat's name for search
     * @return cats list with name - name
     */
    Users getUserByName(String name);


}
