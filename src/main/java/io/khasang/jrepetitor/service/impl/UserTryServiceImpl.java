package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.UserTryDao;
import io.khasang.jrepetitor.entity.UserTry;
import io.khasang.jrepetitor.service.UserTryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userTryService")
public class UserTryServiceImpl implements UserTryService {
    @Autowired
    private UserTryDao userTryDao;

    @Override
    public UserTry addUserTry(UserTry userTry) {
        return userTryDao.create(userTry);
    }

    @Override
    public List<UserTry> getAllUserTrys() {
        return userTryDao.getList();
    }

    @Override
    public UserTry getUserTryById(long id) {
        return userTryDao.getById(id);
    }

    @Override
    public UserTry deleteUserTry(long id) {
        return userTryDao.delete(getUserTryById(id));
    }

}
