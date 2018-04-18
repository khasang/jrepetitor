package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.BasicDao;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T getById(long id) {
        return getSessionFactory().get(initializeAndUnproxy(entityClass), id);
    }

    @Override
    public T delete(T entity) {
        getSessionFactory().delete(entity);
        return entity;
    }

    @Override
    public T create(T entity) {
        getSessionFactory().save(entity);
        return entity;
    }

    @Override
    public List<T> getList() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(initializeAndUnproxy(entityClass));
        Root<T> root = criteriaQuery.from(initializeAndUnproxy(entityClass));

        criteriaQuery.select(root);

        List<T> list = initializeAndUnproxy(getSessionFactory().createQuery(criteriaQuery).list());

        return list;
    }

    public static <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
        }
        return entity;
    }
}
