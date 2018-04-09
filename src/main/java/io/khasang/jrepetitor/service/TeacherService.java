package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.TeacherDTO;
import io.khasang.jrepetitor.entity.Teacher;

import java.util.List;

public interface TeacherService {
    /**
     * adds teacher
     *
     * @param teacher = new teacher for creation in DB
     * @return created teacher
     */
    Teacher addTeacher(Teacher teacher);

    /**
     * receiving all teachers from DB
     *
     * @return all teachers
     */
    List<TeacherDTO> getAllTeachers();

    /**
     * updating teacher in DB
     *
     * @param teacher = new teacher which exchange old one
     * @return new teacher
     */
    Teacher updateTeacher(Teacher teacher);

    /**
     * receive specify teacher by id
     *
     * @param id = uniq teacher id
     * @return specify teacher by id
     */
    Teacher getTeacherById(long id);

    /**
     * delete specify teacher by id
     *
     * @param id = teacher's id for delete
     * @return deleted teacher
     */
    Teacher deleteTeacher(long id);
}
