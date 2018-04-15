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
    public User addUser(User user) throws IllegalArgumentException {
        if (userDao.getUserByLogin(user.getLogin()) == null) {
            return userDao.create(user);
        } else {
            throw new IllegalArgumentException("This login is already engaged, login must be unic");
        }
    }

    @Override
    public User getUserById(long id) {
        return userDao.getById(id);
    }

    public List<User> getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public User deleteUser(long id) {
        return userDao.delete(getUserById(id));
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getList();
    }
}
