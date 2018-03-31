package io.khasang.jrepetitor.controller;


import io.khasang.jrepetitor.entity.Kot;
import io.khasang.jrepetitor.service.KotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/kot")
public class KotController {

    @Autowired
    private KotService kotService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Kot add(@RequestBody Kot kot){
        System.out.println("Got Kot "+kot.getName()+" "+kot.getId());
        return kotService.add(kot);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<Kot> getAllKots(){
        return kotService.getAll();
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Kot update(@RequestBody Kot kot){
        return kotService.update(kot);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Kot get(@PathVariable(value = "id") long id){
        return kotService.get(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE ,produces = "application/json; charset=utf-8")
    @ResponseBody
    public Kot delete(@RequestParam(value = "id") long id){
        return kotService.delete(kotService.get(id));
    }


}
