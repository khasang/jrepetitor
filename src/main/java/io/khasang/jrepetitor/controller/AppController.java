package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.model.Cat;
import io.khasang.jrepetitor.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Controller
public class AppController {
    @Autowired
    private Cat cat;

    @Autowired
    private CreateTable createTable;

//    @RequestMapping("/")
//    public String helloPage(Model model) {
//        model.addAttribute("name", cat.getName());
//        return "hello";
//    }

    @RequestMapping("/")
    public String createTableStatus(Model model) {
        model.addAttribute("status", createTable.createTableStatus());
        return "create";
    }
}
