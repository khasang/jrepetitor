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
     * @param entity = new entity for creating in DB
     * @return created entity
     */
    T create(T entity);

    /**
     * method for recieving all entity
     *
     * @return all entity
     */
    List<T> getList();

    /**
     * method for specify entity by id
     *
     * @param id = uniq entity id
     * @return specify entity by id
     */
    T getById(long id);

    /**
     * method entity cat delete
     *
     * @param entity = entity for delete
     * @return removed entity
     */
    T delete(T entity);

    /**
     * method for entity update
     *
     * @param entity = entity for update
     * @return updated update
     */
    T updateCat(T entity);
}
