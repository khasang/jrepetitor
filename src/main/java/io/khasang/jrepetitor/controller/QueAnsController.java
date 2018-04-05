package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.QueAns;
import io.khasang.jrepetitor.service.QueAnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/queans")
public class QueAnsController {
    @Autowired
    QueAnsService queAnsService;

//реализуем CRUD

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public QueAns addQueAns(@RequestBody QueAns queAns){
        return queAnsService.addQueAns(queAns);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<QueAns> getAllQueAns(){
        return queAnsService.getAllQueAns();
    }

}
