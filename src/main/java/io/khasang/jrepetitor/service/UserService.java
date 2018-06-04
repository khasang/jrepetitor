package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.UserDTOInterface;
//import io.khasang.jrepetitor.dto.impl.UserDTO;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.model.CreationProfileStatusResponseWrapper;
import io.khasang.jrepetitor.model.CreationUserStatusResponseWrapper;

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
    List<UserDTOInterface> getAllUsers();

    /**
     * method for receive specify user by id
     *
     * @param id = uniq user id
     * @return specify user by id
     */
    UserDTOInterface getUserById(long id);

    /**
     * method for user delete
     *
     * @param id = users's id for delete
     * @return removed user
     */
    UserDTOInterface deleteUser(long id);

    /**
     * method for finding user by name
     *
     * @param login = User's name for search
     * @return user list with name - name
     */
    UserDTOInterface getUserByLogin(String login);

    /**
     * method for update user
     *
     * @param user = User  for update
     * @return updated user
     */
    UserDTOInterface updateUser(User user);

    /**
     * method for create user
     *
     * @param user = User  for update
     * @return operation state class
     */
    CreationUserStatusResponseWrapper createUser(User user);

    /**
     * method for update user
     *
     * @param userName = user  for update profile
     * @param profile  = new profile state
     * @return operations state class
     */
    CreationProfileStatusResponseWrapper updateProfile(String userName, Profile profile);

    /**
     * method for check phone existing
     *
     * @param phone
     * @return True if exist
     */
    Boolean checkPhoneExist(String phone);

    /**
     * method for check phone existing
     *
     * @param email
     * @return True if exist
     */
    Boolean checkEmailExist(String email);

    /**
     * method for check phone existing
     *
     * @param login
     * @return True if exist
     */
    Boolean checkLogin(String login);
}
