package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.RightAnsDao;
import io.khasang.jrepetitor.dao.UserDao;
import io.khasang.jrepetitor.entity.RightAns;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.service.RightAnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rightAnsService")
public class RightAnsServiceImpl implements RightAnsService {
    @Autowired
    private RightAnsDao rightAnsDao;

    @Autowired
    private UserDao userDao;

    @Override
    public RightAns addRightAns(String userLogin, RightAns rightAns) {
        User user = userDao.getUserByLogin(userLogin);
        rightAns.setUser(user);
        RightAns createdRightAns = rightAnsDao.create(rightAns);
        return rightAnsDao.create(createdRightAns);
    }

    @Override
    public List<RightAns> getAllRightAns() {
        return rightAnsDao.getList();
    }

    @Override
    public RightAns getRightAnsById(long id) {
        return rightAnsDao.getById(id);
    }
}
