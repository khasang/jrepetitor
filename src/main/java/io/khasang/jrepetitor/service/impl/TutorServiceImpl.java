package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.TutorDao;
import io.khasang.jrepetitor.dto.TutorDTO;
import io.khasang.jrepetitor.entity.Tutor;
import io.khasang.jrepetitor.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tutorService")
public class TutorServiceImpl implements TutorService {
    @Autowired
    private TutorDao tutorDao;
    @Autowired
    private TutorDTO tutorDTO;

    @Override
    public List<TutorDTO> getAllTutors() {
        return tutorDTO.getTutorDTOList(tutorDao.getList());
    }

    @Override
    public Tutor addTutor(Tutor tutor) {
        return tutorDao.create(tutor);
    }

    @Override
    public Tutor updateTutor(Tutor tutor) {
        return tutorDao.update(tutor);
    }

    @Override
    public Tutor getTutorById(long id) {
        return tutorDao.getById(id);
    }

    @Override
    public Tutor deleteTutor(long id) {
        return tutorDao.delete(getTutorById(id));
    }
}
