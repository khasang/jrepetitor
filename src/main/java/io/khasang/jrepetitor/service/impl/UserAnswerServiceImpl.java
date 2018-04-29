package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.UserAnswerDao;
import io.khasang.jrepetitor.entity.UserAnswer;
import io.khasang.jrepetitor.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userAnswerService")
public class UserAnswerServiceImpl implements UserAnswerService {
    @Autowired
    private UserAnswerDao userAnswerDao;

    @Override
    public UserAnswer addUserAnswer(UserAnswer userAnswer) {
        return userAnswerDao.create(userAnswer);
    }

    @Override
    public List<UserAnswer> getAllUserAnswers() {
        return userAnswerDao.getList();
    }

    @Override
    public UserAnswer getUserAnswerById(long id) {
        return userAnswerDao.getById(id);
    }

    @Override
    public UserAnswer deleteUserAnswer(long id) {
        return userAnswerDao.delete(getUserAnswerById(id));
    }
}
