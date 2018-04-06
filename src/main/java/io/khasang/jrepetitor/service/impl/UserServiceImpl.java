package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.UserDao;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User addUser(User user) {
        return userDao.create(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getById(id);
    }

    public List<User> getUserByName(String userName) {
        return userDao.getByName(userName);
    }

    @Override
    public User deleteUser(long id) {
        return userDao.delete(getUserById(id));
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getList();
    }

    @Override
    public User getUserById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User deleteUser(long id) {
        return userDao.delete(getUserById(id));
    }
}
