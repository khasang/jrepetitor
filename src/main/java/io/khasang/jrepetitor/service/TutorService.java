package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.TutorDTO;
import io.khasang.jrepetitor.entity.Tutor;

import java.util.List;

public interface TutorService {
    List<TutorDTO> getAllTutors();

    Tutor addTutor(Tutor tutor);

    Tutor updateTutor(Tutor tutor);

    Tutor getTutorById(long id);

    Tutor deleteTutor(long id);
}
