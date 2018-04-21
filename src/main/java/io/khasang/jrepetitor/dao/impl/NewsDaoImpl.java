package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.NewsDao;
import io.khasang.jrepetitor.entity.News;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl extends BasicDaoImpl<News> implements NewsDao {
    public NewsDaoImpl(Class<News> entityClass) {
        super(entityClass);
    }

    @Override
    public List<News> getNewsByTitle(String title) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT id FROM News WHERE title = :title");
        query.setParameter("title", title);

        List<Long> ids = query.list();
        List<News> newsList = new ArrayList<>(ids.size());
        ids.forEach(id -> newsList.add(getById(id)));

        return newsList;
    }
}
