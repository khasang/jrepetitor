package io.khasang.jrepetitor.dao;

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

}
