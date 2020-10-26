package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.Question;

public interface QuestionDao extends BasicDao<Question> {
    /**
     * method for update question
     *
     * @param question
     * @return user updated user
     */
    Question updateQuestion(Question question);
}
