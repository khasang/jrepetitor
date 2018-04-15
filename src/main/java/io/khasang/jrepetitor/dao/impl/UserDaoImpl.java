package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.UserDao;
import io.khasang.jrepetitor.entity.Users;
import org.hibernate.Session;

;import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class UserDaoImpl extends BasicDaoImpl<Users> implements UserDao {
    public UserDaoImpl(Class<Users> entityClass) {
        super(entityClass);
    }


    public Users getUserByName(String name) {


        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);

        Root<Users> root = criteriaQuery.from(Users.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));

        TypedQuery<Users> typedQuery = session.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }
}
