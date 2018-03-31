package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.DAO.KotDao;
import io.khasang.jrepetitor.entity.Kot;
import io.khasang.jrepetitor.service.KotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("kotService")
public class KotServiceImpl implements KotService{

    @Autowired
    private KotDao dao;

    @Override
    public Kot add(Kot kot) {
        return (Kot)dao.add(kot);
    }

    @Override
    public List<Kot> getAll() {
        return dao.getAll();
    }

    @Override
    public Kot update(Kot kot) {
        return (Kot)dao.update(kot);
    }

    @Override
    public Kot get(long id) {
        return (Kot)dao.get(id);
    }

    @Override
    public Kot delete(Kot kot) {
        return (Kot)dao.delete(kot);
    }
}
