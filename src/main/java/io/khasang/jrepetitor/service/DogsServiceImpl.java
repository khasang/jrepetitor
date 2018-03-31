package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.DAO.DogsDAO;
import io.khasang.jrepetitor.entity.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
 public class DogsServiceImpl implements DogsService {

    @Autowired
    DogsDAO dao;

    @Transactional
    public void insert(Dog dog) {
        dao.insert(dog);
    }

    @Transactional
    public void update(Dog dog) {
        dao.update(dog);
    }

    @Transactional
    public void delete(long dog_id) {
        dao.delete(dog_id);
    }

    @Transactional
    public Dog getDog(long dog_id) {
        return dao.getDog(dog_id);
    }

    @Transactional
    public List<Dog> findAll() {
        return dao.findAll();
    }
}
