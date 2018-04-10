package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.TeacherDTO;
import io.khasang.jrepetitor.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher addTeacher(Teacher teacher);

    List<TeacherDTO> getAllTeachers();

//    List<Teacher> getAllTeachers();

    Teacher getTeacherById(long l);

    Teacher deleteTeacher(long l);
}
