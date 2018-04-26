package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.TutorDTO;
import io.khasang.jrepetitor.entity.Tutor;
import io.khasang.jrepetitor.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/tutor")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<TutorDTO> getAllTutors() {
        return tutorService.getAllTutors();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Tutor addEmployee(@RequestBody Tutor employee) {
        return tutorService.addTutor(employee);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Tutor updateEmployee(@RequestBody Tutor employee) {
        return tutorService.updateTutor(employee);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Tutor getEmployeeById(@PathVariable(value = "id") String id) {
        return tutorService.getTutorById(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Tutor deleteEmployee(@RequestParam(value = "id") String id) {
        return tutorService.deleteTutor(Long.parseLong(id));
    }
}
