package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.CatDao;
import io.khasang.jrepetitor.entity.Cat;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CatDaoImpl extends BasicDaoImpl<Cat> implements CatDao {
    public CatDaoImpl(Class<Cat> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Cat> getCatsByName(String name) {
        //        Native sql
//        Query query = getSessionFactory().createQuery("SELECT c FROM Cat as c WHERE c.name = :name");
//        query.setParameter("name", name);
//        return query.getResultList();

        //HQL
//        return (List<Cat>) getSessionFactory().createQuery("from Cat as c where c.name = ?")
//                .setParameter(0, name).list();

        // Criteria
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Cat> criteriaQuery = criteriaBuilder.createQuery(Cat.class);

        Root<Cat> root = criteriaQuery.from(Cat.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));

        TypedQuery<Cat> typedQuery = session.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }
}
