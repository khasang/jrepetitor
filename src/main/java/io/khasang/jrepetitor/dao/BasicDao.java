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

    /**
     * updating cat in DB
     *
     * @param entity = new entity which exchange old one
     * @return new entity
     */
    T update(T entity);

    /**
     * receive specify entity by id
     *
     * @param id = uniq entity id
     * @return specify entity by id
     */
    T getById(long id);

    /**
     * delete specify entity by id
     *
     * @param entity = entity's id for delete
     * @return deleted entity
     */
    T delete(T entity);
}
