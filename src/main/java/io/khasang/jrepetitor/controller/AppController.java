package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @Autowired
    Cat cat;
    @RequestMapping("/")
    public String helloPage(Model model){
        model.addAttribute("name",cat.getName());
        return "hello";
    }
}
