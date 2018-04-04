package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.QuizDao;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("quizService")
public class QuizServiceImpl implements QuizService{
    @Autowired
    private QuizDao quizDao;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizDao.create(quiz);
    }

    @Override
    public List<Quiz> getAllQuizs() {
        return quizDao.getList();
    }

    @Override
    public Quiz getQuizById(long id) {
        return quizDao.getById(id);
    }

    @Override
    public Quiz deleteQuiz(long id) {
        return quizDao.delete(getQuizById(id));
    }
}
