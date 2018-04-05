package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.QueAnsDao;
import io.khasang.jrepetitor.entity.QueAns;
import io.khasang.jrepetitor.service.QueAnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("queAnsService")
public class QueAnsServiceImpl implements QueAnsService{
    @Autowired
    private QueAnsDao queAnsDao;

    @Override
    public QueAns addQueAns(QueAns queAns) {
        return queAnsDao.create(queAns);
    }

    @Override
    public List<QueAns> getAllQueAns() {
        return queAnsDao.getList();
    }
}
