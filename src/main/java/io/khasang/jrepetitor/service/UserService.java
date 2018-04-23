package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.UserDTO;
import io.khasang.jrepetitor.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserService {
    /**
     * method for add User
     *
     * @param user = new user for creation in DB
     * @return created cat
     */
    User addUser(User user);

    /**
     * method for receiving all users
     *
     * @return all users
     */
    List<UserDTO> getAllUsers();

    /**
     * method for receive specify user by id
     *
     * @param id = uniq user id
     * @return specify user by id
     */
    UserDTO getUserById(long id);

    /**
     * method for user delete
     *
     * @param id = users's id for delete
     * @return removed user
     */
    User deleteUser(long id);

    // TODO: 15.04.2018 add search by name

    /**
     * method for finding user by name
     *
     * @param name = User's name for search
     * @return user list with name - name
     */
    User getUserByLogin(String name);

    /**
     * method for update user
     *
     * @param user = User  for update
     * @return updated user
     */
    User updateUser(User user);

}
