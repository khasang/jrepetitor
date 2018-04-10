package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Pupil;
import io.khasang.jrepetitor.service.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/pupil")
public class PupilController {
    @Autowired
    private PupilService pupilService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Pupil> getAllCars() {
        return pupilService.getAllPupils();
    }
}
