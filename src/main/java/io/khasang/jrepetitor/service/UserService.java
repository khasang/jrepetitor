package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.dto.UserDTO;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.utils.CreationProfileStatus;
import io.khasang.jrepetitor.utils.CreationUserStatus;

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

    /**
     * method for finding user by name
     *
     * @param login = User's name for search
     * @return user list with name - name
     */
    User getUserByLogin(String login);

    /**
     * method for update user
     *
     * @param user = User  for update
     * @return updated user
     */
    User updateUser(User user);

    /**
     * method for create user
     *
     * @param user = User  for update
     * @return operation state class
     */
    CreationUserStatus createUser(User user);

    /**
     * method for update user
     *
     * @param user    = user  for update profile
     * @param profile = new profile state
     * @return operations state class
     */
    CreationProfileStatus updateProfile(User user, Profile profile);

    /**
     * method for get user
     *
     * @param userName = unic user name
     * @return specify user by naem
     */
    List<User> getUserByName(String userName);
}
