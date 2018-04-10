package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.Apprentice;

import java.util.List;

public interface ApprenticeService {

    List<Apprentice> getAllApprentice();

    Apprentice addApprentice(Apprentice apprentice);
}
