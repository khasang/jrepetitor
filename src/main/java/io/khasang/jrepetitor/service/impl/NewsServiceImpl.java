package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.NewsDao;
import io.khasang.jrepetitor.entity.News;
import io.khasang.jrepetitor.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public News addNews(News news) {
        return newsDao.create(news);
    }

    @Override
    public News getNewsById(long id) {
        return newsDao.getById(id);
    }

    @Override
    public List<News> getNewsByTitle(String newsTitle) {
        return newsDao.getNewsByTitle(newsTitle);
    }

    @Override
    public News deleteNews(long id) {
        return newsDao.delete(getNewsById(id));
    }
}
