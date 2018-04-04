package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.QuestionDao;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Override
    public Question addQuestion(Question question) {
        return questionDao.create(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.getList();
    }

    @Override
    public Question getQuestionById(long id) {
        return questionDao.getById(id);
    }

    @Override
    public Question deleteQuestion(long id) {
        return questionDao.delete(getQuestionById(id));
    }
}
