package io.khasang.jrepetitor.DAO;

import io.khasang.jrepetitor.entity.Kot;
import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {

    Session getSession();

    T add(T entity);

    List<T> getAll();

    T update(T entity);

    T get(long id);

    T delete(T entity);
}
