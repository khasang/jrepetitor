package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @Autowired

    private Cat cat;

    // Фабричный метод бинов.
    // Cat cat = new Cat();

    @RequestMapping("/")
    public String helloPage(Model model) {
        model.addAttribute("name", cat.getName());
        System.out.println(cat.getName());
        return "hello";
    }
}
