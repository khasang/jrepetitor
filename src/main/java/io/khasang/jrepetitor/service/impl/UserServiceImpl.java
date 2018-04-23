package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.UserDao;
import io.khasang.jrepetitor.dto.ProfileDTO;
import io.khasang.jrepetitor.dto.UserDTO;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDTO userDTO;

    @Override
    public User addUser(User user) {
        return userDao.create(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDTO.getUserDTOList(userDao.getList());
    }

    @Override
    public UserDTO getUserById(long id) {
        return userDTO.getUserDTO(userDao.getById(id));
    }

    @Override
    public User deleteUser(long id) {
        return userDao.delete(userDTO.getUser(getUserById(id)));
    }

    @Override
    public User getUserByLogin(String name) {
        return userDao.getUserByLogin(name);
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }
}
