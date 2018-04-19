package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.News;

public interface NewsDao extends BasicDao<News> {

    /**
     * for finding news by title
     *
     * @param title news' title for search
     * @return news according this title
     */
    News getNewsByTitle(String title);
}
