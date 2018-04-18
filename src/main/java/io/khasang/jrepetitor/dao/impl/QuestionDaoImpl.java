package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.QuestionDao;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl extends BasicDaoImpl<Question> implements QuestionDao {
    public QuestionDaoImpl(Class<Question> entityClass) {
        super(entityClass);
    }

    @Override
    public Question getById(long id) {
        Question question = getSessionFactory().get(Question.class, id);
        return initializeAndUnproxy(question);
    }

    @Override
    public Question delete(Question entity) {
        return super.delete(entity);
    }

    @Override
    public Question create(Question entity) {
        return super.create(entity);
    }

    @Override
    public List<Question> getList() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Question> criteriaQuery = builder.createQuery(Question.class);
        Root<Question> root = criteriaQuery.from(Question.class);

        criteriaQuery.select(root);

        List<Question> list = getSessionFactory().createQuery(criteriaQuery).list();

        List<Question> questions = new ArrayList<>();
        for (Question question:list) {
            questions.add(initializeAndUnproxy(question));
        }

        return questions;
    }


}