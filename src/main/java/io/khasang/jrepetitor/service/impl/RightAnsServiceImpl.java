package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.RightAnsDao;
import io.khasang.jrepetitor.entity.RightAns;
import io.khasang.jrepetitor.service.RightAnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rightAnsService")
public class RightAnsServiceImpl implements RightAnsService {
    @Autowired
    private RightAnsDao rightAnsDao;


    @Override
    public RightAns addRightAns(RightAns rightAns) {
        return rightAnsDao.create(rightAns);
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
