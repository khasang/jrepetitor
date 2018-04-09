package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherDTO {
    private long id;

    private String name;
    private String temper;

    private List<StudentDTO> studentDTOList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemper() {
        return temper;
    }

    public void setTemper(String temper) {
        this.temper = temper;
    }

    public void setStudentDTOList(List<StudentDTO> studentDTOList) {
        this.studentDTOList = studentDTOList;
    }

    public List<StudentDTO> getStudentDTOList() {
        return studentDTOList;
    }

    public List<TeacherDTO> getTeacherDTOList(List<Teacher> teacherList) {
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        teacherList.forEach(teacher -> {
            List<StudentDTO> studentDTOList = new ArrayList<>();
            TeacherDTO teacherDTO = new TeacherDTO();

            teacherDTO.setId(teacher.getId());
            teacherDTO.setName(teacher.getName());
            teacherDTO.setTemper(teacher.getTemper());

            teacher.getStudents().forEach(student -> {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(student.getId());
                studentDTO.setName(student.getName());
                studentDTO.setIq(student.getIq());

                studentDTOList.add(studentDTO);
            });
            teacherDTO.setStudentDTOList(studentDTOList);
            teacherDTOList.add(teacherDTO);
        });

        return teacherDTOList;
    }
}
