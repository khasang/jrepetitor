package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.ApprenticeDto;
import io.khasang.jrepetitor.entity.Apprentice;

import java.util.List;

public interface ApprenticeService {

    List<ApprenticeDto> getAllApprentice();

    Apprentice addApprentice(Apprentice apprentice);
}
