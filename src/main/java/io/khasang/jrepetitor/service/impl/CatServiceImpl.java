package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.CatDao;
import io.khasang.jrepetitor.entity.Cat;
import io.khasang.jrepetitor.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("catService")
public class CatServiceImpl implements CatService {
    @Autowired
    private CatDao catDao;

    @Override
    public Cat addCat(Cat cat) {
        return catDao.create(cat);
    }

    @Override
    public List<Cat> getAllCats() {
        return catDao.getList();
    }

    @Override
    public Cat getCatById(long id) {
        return catDao.getById(id);
    }

    @Override
    public Cat deleteCat(long id) {
        return catDao.delete(getCatById(id));
    }
}