package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.QueAns;

import java.util.List;

public interface QueAnsService {

    /*
    * method for add questions and ansvers in table
    *
    * */
    QueAns addQueAns(QueAns queAns);


    /*
    * method for getting all questions and answers
    *
    * */
    List<QueAns> getAllQueAns();
}
