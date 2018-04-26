package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Student;
import io.khasang.jrepetitor.entity.Tutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TutorDTO {
    private long id;
    private String name;
    private List<StudentDTO> studentDTOList = new ArrayList<>();


    public List<StudentDTO> getStudentDTOList() {
        return studentDTOList;
    }

    public void setStudentDTOList(List<StudentDTO> studentDTOList) {
        this.studentDTOList = studentDTOList;
    }

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

    public List<TutorDTO> getTutorDTOList(List<Tutor> tutorList) {
        List<TutorDTO> tutorDTOList = new ArrayList<>();

        for (Tutor tutor : tutorList) {
            List<StudentDTO> studentDTOList = new ArrayList<>();
            TutorDTO tutorDTO = new TutorDTO();

            tutorDTO.setId(tutor.getId());
            tutorDTO.setName(tutor.getName());

            for (Student student : tutor.getStudentList()) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(student.getId());
                studentDTO.setName(student.getName());

                studentDTOList.add(studentDTO);
            }

            tutorDTO.setStudentDTOList(studentDTOList);
            tutorDTOList.add(tutorDTO);
        }

        return tutorDTOList;
    }
}
