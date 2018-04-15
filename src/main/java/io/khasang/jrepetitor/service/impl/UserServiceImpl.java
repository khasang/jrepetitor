package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.UserDao;
import io.khasang.jrepetitor.entity.Users;
import io.khasang.jrepetitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public Users getUserById(long id) {
        return userDao.getById(id);
    }

    @Override
    public Users getUserByName(String s) {
        return userDao.getUserByName(s);
    }
}
