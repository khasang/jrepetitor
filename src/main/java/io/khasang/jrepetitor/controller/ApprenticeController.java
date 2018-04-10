package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Apprentice;
import io.khasang.jrepetitor.service.ApprenticeService;
import io.khasang.jrepetitor.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "/apprentice")
@Controller
public class ApprenticeController {

    @Autowired
    private ApprenticeService apprenticeService;

    @RequestMapping(value = "/all",method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    public List<Apprentice> getApprenticeAll()
    {
        return apprenticeService.getAllApprentice();
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json;charset=utf8")
    @ResponseBody
    public Apprentice addCat(@RequestBody Apprentice apprentice)
    {

        return apprenticeService.addApprentice(apprentice);
    }
}
