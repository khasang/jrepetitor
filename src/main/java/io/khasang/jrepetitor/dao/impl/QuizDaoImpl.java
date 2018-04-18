package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.QuizDao;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.entity.Quiz;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class QuizDaoImpl extends BasicDaoImpl<Quiz> implements QuizDao {
    public QuizDaoImpl(Class<Quiz> entityClass) {
        super(entityClass);
    }

    @Override
    public Quiz getById(long id) {
        return super.getById(id);
    }

    @Override
    public Quiz delete(Quiz entity) {
        return super.delete(entity);
    }

    @Override
    public Quiz create(Quiz entity) {
        return super.create(entity);
    }

    @Override
    public List<Quiz> getList() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Quiz> criteriaQuery = builder.createQuery(Quiz.class);
        Root<Quiz> root = criteriaQuery.from(Quiz.class);

        criteriaQuery.select(root);

        List<Quiz> list = getSessionFactory().createQuery(criteriaQuery).list();

        List<Quiz> quizs = new ArrayList<>();
        for (Quiz quiz:list) {
            quizs.add(initializeAndUnproxy(quiz));
        }

        return quizs;

    }
}
