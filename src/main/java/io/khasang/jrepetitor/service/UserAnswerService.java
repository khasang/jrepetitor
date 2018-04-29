package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.UserAnswer;

import java.util.List;

public interface UserAnswerService {
    /**
     * method for add userAnswer
     *
     * @param userAnswer = new userAnswer for creation in DB
     * @return created userAnswer
     */
    UserAnswer addUserAnswer(UserAnswer userAnswer);

    /**
     * method for receiving all userAnswers
     *
     * @return all userAnswers
     */
    List<UserAnswer> getAllUserAnswers();

    /**
     * method for receive specify userAnswer by id
     *
     * @param id = uniq userAnswer id
     * @return specify userAnswer by id
     */
    UserAnswer getUserAnswerById(long id);

    /**
     * method for userAnswer delete
     *
     * @param id = userAnswer's id for delete
     * @return removed userAnswer
     */
    UserAnswer deleteUserAnswer(long id);
}
