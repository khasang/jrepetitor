package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.QuestionDao;
import io.khasang.jrepetitor.dao.QuizDao;
import io.khasang.jrepetitor.dto.QuestionDTOInterface;
import io.khasang.jrepetitor.dto.impl.QuestionDTOImpl;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.model.wrappers.QuestionByQuizIdRequestWrapper;
import io.khasang.jrepetitor.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuestionDTOImpl questionDTO;

    @Autowired
    private QuizDao quizDao;


    @Override
    public QuestionDTOInterface addQuestion(Question question) {
        return questionDTO.getQuestionDTO(questionDao.create(question));
    }

    @Override
    public List<QuestionDTOInterface> getAllQuestions() {
        return questionDTO.getQuestionDTOList(questionDao.getList());
    }

    @Override
    public QuestionDTOInterface getQuestionById(long id) {
        return questionDTO.getQuestionDTO(questionDao.getById(id));
    }

    @Override
    public QuestionDTOInterface deleteQuestion(long id) {
        Question question = questionDao.getById(id);
        if (question == null) {
            return null;
        } else {
            Question deletedQuestion = questionDao.delete(questionDao.getById(id));
            return questionDTO.getQuestionDTO(deletedQuestion);
        }
    }

    @Override
    public QuestionDTOInterface addQuestionByQuizId(QuestionByQuizIdRequestWrapper questionByQuizIdRequestWrapper) {
        Quiz quiz = quizDao.getById(questionByQuizIdRequestWrapper.getId());
        if (quiz == null) {
            return null;
        }

        Question question = questionDao.create(questionByQuizIdRequestWrapper.getQuestion());
        quiz.addQuestion(question);
        quizDao.update(quiz);
        question.setQuiz(quiz);
        questionDao.updateQuestion(question);
        return questionDTO.getQuestionDTO(question);
    }
}
