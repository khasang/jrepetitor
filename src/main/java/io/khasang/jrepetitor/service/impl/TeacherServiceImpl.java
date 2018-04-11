package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.TeacherDao;
import io.khasang.jrepetitor.dto.TeacherDto;
import io.khasang.jrepetitor.entity.Teacher;
import io.khasang.jrepetitor.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    TeacherDto teacherDto;

    @Override
    public List<TeacherDto> getAllTeacher() {
        return teacherDto.getTeacherDTOList(teacherDao.getList());
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherDao.create(teacher);
    }
}
