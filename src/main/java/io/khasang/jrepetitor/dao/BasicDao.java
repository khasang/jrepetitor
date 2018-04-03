package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.Cat;
import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {

    /**
     * receiving session
     */
    Session getSessionFactory();

    /**
     * method for add entity
     *
     * @param entity = new cat for creation in DB
     * @return created entity
     */
    T create(T entity);

    /**
     * method for receiving all entity
     *
     * @return all entity
     */
    List<T> getList();

    /**
     * method for receive specify entity by id
     *
     * @param id = unique entity id
     * @return received entity
     */
    T getById(long id);

    /**
     * method for deletion specify entity by id
     *
     * @param entity = unique entity id
     * @return deleted entity
     */
    T delete(T entity);
}
