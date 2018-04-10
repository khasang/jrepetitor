package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.ApprenticeDao;
import io.khasang.jrepetitor.dao.TeacherDao;
import io.khasang.jrepetitor.entity.Apprentice;
import io.khasang.jrepetitor.service.ApprenticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("apprenticeService")
public class ApprenticeServiceImpl implements ApprenticeService {
    @Autowired
    ApprenticeDao apprenticeDao;
    @Override
    public List<Apprentice> getAllApprentice() {
        return apprenticeDao.getList();
    }

    @Override
    public Apprentice addApprentice(Apprentice apprentice) {
        return apprenticeDao.create(apprentice);
    }
}
