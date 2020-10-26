package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.RightAns;

import java.util.List;

public interface RightAnsService {

    RightAns addRightAns(String login, RightAns rightAns);

    List<RightAns> getAllRightAns();

    RightAns getRightAnsById(long id);
}
