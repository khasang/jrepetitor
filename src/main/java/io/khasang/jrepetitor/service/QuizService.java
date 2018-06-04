package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.QuizDTOInterface;
import io.khasang.jrepetitor.dto.QuizPreviewDTOInterface;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.model.QuizByGroupIdRequestWrapper;

import java.util.List;

public interface QuizService {
    /**
     * method for add quiz
     *
     * @param quiz = new quiz for creation in DB
     * @return created quiz
     */
    Quiz addQuiz(Quiz quiz);

    /**
     * method for receiving all quizs
     *
     * @return all quizs
     */
    List<QuizDTOInterface> getAllQuizs();

    /**
     * method for receive specify quiz by id
     *
     * @param id = uniq quiz id
     * @return specify quiz by id
     */
    QuizDTOInterface getQuizById(long id);

    /**
     * method for quiz delete
     *
     * @param id = quiz's id for delete
     * @return removed quiz
     */
    Quiz deleteQuiz(long id);

    /**
     * method for receiving preview all quizzes
     *
     * @return all quizzes preview
     */
    List<QuizPreviewDTOInterface> getAllQuizzesPreview();

    /**
     * method for receiving preview quiz by id
     *
     * @param id = uniq quiz id
     * @return specify prew quiz by id
     */
    QuizPreviewDTOInterface getQuizPreviewById(long id);

    /**
     * method for add quiz in group
     *
     * @param quizByGroupIdRequestWrapper - wrapper includes group id and Quiz entity
     * @return created quiz
     */
    QuizDTOInterface createQuizByGroupID(QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper);
}
