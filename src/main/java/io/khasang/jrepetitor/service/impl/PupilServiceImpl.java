package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.entity.Pupil;
import io.khasang.jrepetitor.service.PupilService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pupilService")
public class PupilServiceImpl implements PupilService {
    @Override
    public List<Pupil> getAllPupils() {
        return null;
    }
}
