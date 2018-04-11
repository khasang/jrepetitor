package io.khasang.jrepetitor.dto;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    private List<Teacher> teacherList = new ArrayList<Teacher>();

}
