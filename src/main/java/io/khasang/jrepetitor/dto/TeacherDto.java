package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Apprentice;
import io.khasang.jrepetitor.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherDto {
   private String name;
   private long id;
   private List<ApprenticeDto> apprenticeDtoList =  new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ApprenticeDto> getApprenticeDTOList() {
        return apprenticeDtoList;
    }

    public void setApprenticeDtoList(List<ApprenticeDto> apprenticeDtoList) {
        this.apprenticeDtoList = apprenticeDtoList;
    }

    public List<TeacherDto> getTeacherDTOList(List<Teacher> teacherList) {
        List<TeacherDto> teacherDtoList = new ArrayList<>();

        for (Teacher teacher : teacherList) {
            List<ApprenticeDto> apprenticeDtoList = new ArrayList<>();

            TeacherDto teacherDto = new TeacherDto();
            teacherDto.setId(teacher.getId());
            teacherDto.setName(teacher.getName());
            

            for (Apprentice apprentice : teacher.getApprenticeList()) {
                ApprenticeDto apprenticeDto = new ApprenticeDto();
                apprenticeDto.setId(apprentice.getId());

                apprenticeDtoList.add(apprenticeDto);
            }

            teacherDto.setApprenticeDtoList(apprenticeDtoList);
            teacherDtoList.add(teacherDto);
        }

        return teacherDtoList;
    }
}
