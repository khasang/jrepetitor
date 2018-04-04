package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.Quiz;

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
    List<Quiz> getAllQuizs();

    /**
     * method for receive specify quiz by id
     *
     * @param id = uniq quiz id
     * @return specify quiz by id
     */
    Quiz getQuizById(long id);

    /**
     * method for quiz delete
     *
     * @param id = quiz's id for delete
     * @return removed quiz
     */
    Quiz deleteQuiz(long id);
}
