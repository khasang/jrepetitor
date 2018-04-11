package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.TeacherDto;
import io.khasang.jrepetitor.entity.Cat;

import io.khasang.jrepetitor.entity.Teacher;
import io.khasang.jrepetitor.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "/teacher")
@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @RequestMapping(value = "/all",method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    public List<TeacherDto> getTeacherAll()
    {
        return teacherService.getAllTeacher();
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json;charset=utf8")
    @ResponseBody
    public Teacher addCat(@RequestBody Teacher teacher)
    {

        return teacherService.addTeacher(teacher);
    }
}
