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
    public List<User> getUserByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT id FROM User WHERE name = :userName");
        query.setParameter("userName", name);

        List<Long> ids = query.list();
        List<User> usersList = new ArrayList<>(ids.size());
        ids.forEach(id -> usersList.add(getById(id)));

        return usersList;
    }

    @Override
    public User getUserByLogin(String login) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT id FROM User WHERE login = :login");
        query.setParameter("login", login);

        if (query.list().size() == 0) {
            return null;
        } else {
            long id = (long) query.list().get(0);
            return getById(id);
        }

    }
}
