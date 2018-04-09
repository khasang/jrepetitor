package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.TeacherDTO;
import io.khasang.jrepetitor.entity.Teacher;
import io.khasang.jrepetitor.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Teacher updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Teacher getTeacherById(@PathVariable(value = "id") String id) {
        return teacherService.getTeacherById(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Teacher deleteTeacher(@RequestParam(value = "id") String id) {
        return teacherService.deleteTeacher(Long.parseLong(id));
    }
}
