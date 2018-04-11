package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class ApprenticeDto {

    private long id;

    private String name;

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

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    private List<Teacher> teacherList = new ArrayList<Teacher>();

}
