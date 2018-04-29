package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.QuizDao;
import io.khasang.jrepetitor.dto.QuizDTO;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("quizService")
public class QuizServiceImpl implements QuizService{
    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuizDTO quizDTO;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizDao.create(quiz);
    }

    @Override
    public List<QuizDTO> getAllQuizs() {
        return quizDTO.getQuizDTOList(quizDao.getList());
    }

    @Override
    public QuizDTO getQuizById(long id) {
        return quizDTO.getQuiz(quizDao.getById(id));
    }

    @Override
    public Quiz deleteQuiz(long id) {
        return quizDao.delete(quizDao.getById(id));
    }
}
