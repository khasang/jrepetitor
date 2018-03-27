package io.khasang.jrepetitor.dao;

import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {

    /**
     * receiving session
     * @return session
     */
    Session getSessionFactory();

    /**
     * adds cat
     *
     * @param entity = new entity for creation in DB
     * @return created entity
     */
    T create(T entity);

    /**
     * receiving all entities from DB
     *
     * @return all entities
     */
    List<T> getList();
}
