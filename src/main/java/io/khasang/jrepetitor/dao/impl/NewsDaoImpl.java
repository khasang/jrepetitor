package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.NewsDao;
import io.khasang.jrepetitor.entity.News;
import org.hibernate.query.Query;

public class NewsDaoImpl extends BasicDaoImpl<News> implements NewsDao {
    public NewsDaoImpl(Class<News> entityClass) {
        super(entityClass);
    }

    @Override
    public News getNewsByTitle(String title) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT id FROM News WHERE title = :title");
        query.setParameter("title", title);

        if (query.list().isEmpty()) {
            return null;
        } else {
            long id = (long) query.list().get(0);
            return getById(id);
        }
    }
}
