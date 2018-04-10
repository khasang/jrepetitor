package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Pupil;
import io.khasang.jrepetitor.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherDTO {
    private long id;
    private String name;
    private String description;
    private List<PupilDTO> pupilDTOList = new ArrayList<>();

    public List<PupilDTO> getPupilDTOList() {
        return pupilDTOList;
    }

    public List<TeacherDTO> getTeacherDTOList(List<Teacher> teacherList) {
        List<TeacherDTO> teacherDTOList = new ArrayList<>();

        for (Teacher teacher : teacherList) {
            List<PupilDTO> pupilDTOList = new ArrayList<>();

            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(teacher.getId());
            teacherDTO.setName(teacher.getName());
            teacherDTO.setDescription(teacher.getDescription());

            for (Pupil pupil : teacher.getCarList()) {
                PupilDTO pupilDTO = new PupilDTO();
                pupilDTO.setId(pupil.getId());
                pupilDTO.setName(pupil.getName());
                pupilDTO.setYear(pupil.getYear());

                pupilDTOList.add(pupilDTO);
            }

            teacherDTO.setPupilDTOList(pupilDTOList);
            teacherDTOList.add(teacherDTO);
        }

        return teacherDTOList;
    }

    public void setPupilDTOList(List<PupilDTO> pupilDTOList) {
        this.pupilDTOList = pupilDTOList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
