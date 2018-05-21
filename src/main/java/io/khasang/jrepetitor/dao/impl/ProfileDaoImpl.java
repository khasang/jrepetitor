package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.ProfileDao;
import io.khasang.jrepetitor.entity.Profile;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProfileDaoImpl extends BasicDaoImpl<Profile> implements ProfileDao {
    public ProfileDaoImpl(Class<Profile> entityClass) {
        super(entityClass);
    }

    @Override
    public Profile getProfileByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Profile> criteriaQuery = criteriaBuilder.createQuery(Profile.class);

        Root<Profile> root = criteriaQuery.from(Profile.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"), email));

        TypedQuery<Profile> typedQuery = session.createQuery(criteriaQuery);
        List<Profile> resultList = typedQuery.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    @Override
    public Profile getProfileByPhone(String phoneNumber) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Profile> criteriaQuery = criteriaBuilder.createQuery(Profile.class);

        Root<Profile> root = criteriaQuery.from(Profile.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber));

        TypedQuery<Profile> typedQuery = session.createQuery(criteriaQuery);
        List<Profile> resultList = typedQuery.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

}
