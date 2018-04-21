package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.News;

import java.util.List;

public interface NewsService {
    /**
     * method for add news
     *
     * @param news = new user for creation in DB
     * @return created news
     */
    News addNews(News news);

    /**
     * method for receive specify news by id
     *
     * @param id = uniq news id
     * @return specify news by id
     */
    News getNewsById(long id);

    /**
     * method for get news by title
     *
     * @param newsTitle = unic news title
     * @return specify news by title
     */
    List<News> getNewsByTitle(String newsTitle);

    /**
     * method for news delete
     *
     * @param id = news' id for delete
     * @return removed news
     */
    News deleteNews(long id);
}
