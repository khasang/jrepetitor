package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.News;

import java.util.List;

public interface NewsDao extends BasicDao<News> {

    /**
     * for finding news by title
     *
     * @param title news' title for search
     * @return news according this title
     */
    List<News> getNewsByTitle(String title);
}
