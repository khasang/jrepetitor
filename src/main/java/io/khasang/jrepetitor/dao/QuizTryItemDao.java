package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.QuizTryItem;

public interface QuizTryItemDao extends BasicDao<QuizTryItem> {
    QuizTryItem update(QuizTryItem quizTryItem);
}
