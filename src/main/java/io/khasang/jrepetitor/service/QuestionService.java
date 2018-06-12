package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.QuestionDTOInterface;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.model.QuestionByQuizIdRequestWrapper;

import java.util.List;

public interface QuestionService {
    /**
     * method for add Question
     *
     * @param Question = new Question for creation in DB
     * @return created Question
     */
    QuestionDTOInterface addQuestion(Question Question);

    /**
     * method for receiving all Questions
     *
     * @return all Questions
     */
    List<QuestionDTOInterface> getAllQuestions();

    /**
     * method for receive specify Question by id
     *
     * @param id = uniq Question id
     * @return specify Question by id
     */
    QuestionDTOInterface getQuestionById(long id);

    /**
     * method for Question delete
     *
     * @param id = Question's id for delete
     * @return removed Question
     */
    QuestionDTOInterface deleteQuestion(long id);


    /**
     * add question to quiz id
     *
     * @param questionByQuizIdRequestWrapper
     * @return created Question
     */
    QuestionDTOInterface addQuestionByQuizId(QuestionByQuizIdRequestWrapper questionByQuizIdRequestWrapper);
}
