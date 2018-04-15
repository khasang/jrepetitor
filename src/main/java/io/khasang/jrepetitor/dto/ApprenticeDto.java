package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Apprentice;
import io.khasang.jrepetitor.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ApprenticeDto {

    private long id;

    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private List<TeacherDto> teacherDtos = new ArrayList<>();

    public List<TeacherDto> getTeacherDtos() {
        return teacherDtos;
    }

    public void setTeacherDtos(List<TeacherDto> teacherDtos) {
        this.teacherDtos = teacherDtos;    }

    private List<ApprenticeDto> apprentices = new ArrayList<>();

    public List<ApprenticeDto> getApprentices( List<Apprentice> apprenticeList) {

        List <ApprenticeDto> apprenticeDtoList = new ArrayList<>();

        for(Apprentice apprentice : apprenticeList)
        {
          List <TeacherDto> teacherDtoList = new ArrayList<>();
          ApprenticeDto apprenticeDto = new ApprenticeDto();
          apprenticeDto.setId(apprentice.getId());
          apprenticeDto.setName(apprentice.getName());

          for(Teacher teacher : apprentice.getTeacherList())
          {
              TeacherDto teacherDto = new TeacherDto();
              teacherDto.setId(teacher.getId());
              teacherDto.setName(teacher.getName());
              teacherDtoList.add(teacherDto);
          }
            apprenticeDto.setTeacherDtos(teacherDtoList);
          apprenticeDtoList.add(apprenticeDto);
        }

        return apprenticeDtoList;
    }

    public void setApprentices(List<ApprenticeDto> apprentices) {
        this.apprentices = apprentices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
