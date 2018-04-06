package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.UserDao;
import io.khasang.jrepetitor.entity.User;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    public UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }

    @Override
    public List<User> getByName(String userName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT id FROM User WHERE name = :userName");
        query.setParameter("userName", userName);

        List<Long> ids = query.list();
        List<User> usersList = new ArrayList<>(ids.size());
        ids.forEach(id -> usersList.add(getById(id)));

        return usersList;
    }
}
