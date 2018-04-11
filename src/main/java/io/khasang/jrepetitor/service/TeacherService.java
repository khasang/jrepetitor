package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.TeacherDto;
import io.khasang.jrepetitor.entity.Cat;
import io.khasang.jrepetitor.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<TeacherDto> getAllTeacher();

    Teacher addTeacher(Teacher teacher);
}
