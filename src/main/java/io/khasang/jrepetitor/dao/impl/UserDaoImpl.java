package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.UserDao;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("login"), login));

        TypedQuery<User> typedQuery = session.createQuery(criteriaQuery);
        List<User> resultList = typedQuery.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    @Override
    public User updateUser(User user) {
        super.getSessionFactory().update(user);
        return user;
    }
}
