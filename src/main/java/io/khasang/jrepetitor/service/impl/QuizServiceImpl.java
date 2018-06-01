package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.QuizDao;
import io.khasang.jrepetitor.dto.QuizDTOInterface;
import io.khasang.jrepetitor.dto.QuizPreviewDTOInterface;
import io.khasang.jrepetitor.dto.impl.QuizDTOImpl;
import io.khasang.jrepetitor.dto.impl.QuizPreviewDTOImpl;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("quizService")
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuizDTOImpl quizDTO;
    @Autowired
    private QuizPreviewDTOImpl quizPreviewDTO;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizDao.create(quiz);
    }

    @Override
    public List<QuizDTOInterface> getAllQuizs() {
        return quizDTO.getQuizDTOList(quizDao.getList());
    }

    @Override
    public QuizDTOInterface getQuizById(long id) {
        return quizDTO.getQuiz(quizDao.getById(id));
    }

    @Override
    public Quiz deleteQuiz(long id) {
        return quizDao.delete(quizDao.getById(id));
    }

    @Override
    public List<QuizPreviewDTOInterface> getAllQuizzesPreview() {
        List<Quiz> quizzes = quizDao.getList();
        return quizPreviewDTO.getListQuizPreviewDTO(quizzes);
    }

    @Override
    public QuizPreviewDTOInterface getQuizPreviewById(long id) {
        Quiz currentQuiz = quizDao.getById(id);
        return quizPreviewDTO.getPreviewDTO(currentQuiz);
    }
}
