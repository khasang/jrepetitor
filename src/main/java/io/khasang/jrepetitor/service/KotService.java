package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.Kot;

import java.util.List;

public interface KotService {

    Kot add(Kot kot);

    List<Kot> getAll();

    Kot update(Kot kot);

    Kot get(long id);

    Kot delete(Kot kot);
}
