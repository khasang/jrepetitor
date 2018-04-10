package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.entity.Cat;
import io.khasang.jrepetitor.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeacher();

    Teacher addTeacher(Teacher teacher);
}
