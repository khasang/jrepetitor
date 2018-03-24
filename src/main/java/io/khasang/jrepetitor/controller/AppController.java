package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.model.Cat;
import io.khasang.jrepetitor.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
    @Autowired
    Cat cat;
    @Autowired
    private CreateTable createTable;
    @RequestMapping("/")
    public String helloPage(Model model){
        model.addAttribute("name",cat.getName());
        return "hello";
    }

    @RequestMapping("/create")
    public String createTableStatus(Model model)
    {
        model.addAttribute("status",createTable.createTableStatus());
        return "create";
    }
    @RequestMapping(value = "/password/{password}",method = RequestMethod.GET)
    public  String getPasswordHelper(@PathVariable("password")String password, Model model )
    {

        model.addAttribute("password",new BCryptPasswordEncoder().encode(password));
        return "pwd";
    }
}
