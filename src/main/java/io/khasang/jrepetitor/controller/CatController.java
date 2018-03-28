package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Cat;
import io.khasang.jrepetitor.service.CatService;
import io.khasang.jrepetitor.service.impl.CatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "/cat")
@Controller
public class CatController {
    @Autowired
    private CatService catService;

    //add
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json;charset=utf8")
    @ResponseBody
    public Cat addCat(@RequestBody Cat cat)
    {

        return catService.addCat(cat);
    }

    //all

    @RequestMapping(value = "/all",method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    public List<Cat> getCatAll()
    {
        return catService.getAllCats();
    }

}
