package io.khasang.jrepetitor.DAO.impl;

import io.khasang.jrepetitor.DAO.BasicDao;
import io.khasang.jrepetitor.entity.Kot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public class BasicDaoImpl<T> implements BasicDao<T> {

    private final Class<T> entityClass;

    @Autowired
    protected SessionFactory sessionFactory;

    public BasicDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T add(T entity) {
        getSession().save(entity);
        return entity;
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> root=query.from(entityClass);
        query.select(root);

        List<T> list = getSession().createQuery(query).list();
        return list;
    }

    @Override
    public T update(T entity) {
        getSession().update(entity);
        return entity;
    }

    @Override
    public T get(long id) {
        return getSession().get(entityClass,id);
    }

    @Override
    public T delete(T entity) {
        getSession().delete(entity);
        return entity;
    }
}
