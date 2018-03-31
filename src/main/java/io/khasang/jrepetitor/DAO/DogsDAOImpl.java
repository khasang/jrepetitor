package io.khasang.jrepetitor.DAO;

import io.khasang.jrepetitor.entity.Dog;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class  DogsDAOImpl implements DogsDAO{

    @Autowired
    private SessionFactory session;

    @Override
    public void insert(Dog dog) {
        session.getCurrentSession().save(dog);
    }

    @Override
    public void update(Dog dog) {
        session.getCurrentSession().update(dog);
    }

    @Override
    public void delete(long dog_id) {
        session.getCurrentSession().delete(getDog(dog_id));
    }

    @Override
    public Dog getDog(long dog_id) {
        return (Dog)session.getCurrentSession().get(Dog.class,dog_id);
    }

    @Override
    public List<Dog> findAll() {
         return session.getCurrentSession().createQuery("from Dog").list();
    }
}
