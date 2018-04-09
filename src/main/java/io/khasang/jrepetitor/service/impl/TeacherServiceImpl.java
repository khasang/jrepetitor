package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.TeacherDao;
import io.khasang.jrepetitor.dto.TeacherDTO;
import io.khasang.jrepetitor.entity.Teacher;
import io.khasang.jrepetitor.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeacherDTO teacherDTO;


    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherDao.create(teacher);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return teacherDTO.getTeacherDTOList(teacherDao.getList());
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherDao.update(teacher);
    }

    @Override
    public Teacher getTeacherById(long id) {
        return teacherDao.getById(id);
    }

    @Override
    public Teacher deleteTeacher(long id) {
        return teacherDao.delete(getTeacherById(id));
    }
}
