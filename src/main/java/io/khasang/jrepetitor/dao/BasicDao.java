package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.Cat;
import org.hibernate.Session;

import java.util.List;

public interface BasicDao <T> {

    /**
     *
     * receiving session
     */

    Session getSessionFactory();
    /**
     * method for add cat
     * @param entity = new entity for creation in DB
     * @return created entity
     **/
    T create(T entity);

    /**
     *
     * method for receiving all entity for DB
     * @return all entity
     */
    List<T> getList();

    /**
     * method for get cat by id
     * @param id = id entity for get
     * @return return entity by id
     */

    T getById(long id);

    /**method for delete cat by id
     *
     * @param entity = entity id delete
     * @return deleted entity
     */
    T delete(T entity);
}
