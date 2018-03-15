package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.DAO.CatsDAO;
import io.khasang.jrepetitor.model.Cat;
import io.khasang.jrepetitor.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @Autowired
    private Cat cat;

    @Autowired
    private CreateTable createTable;

    @Autowired
    private CatsDAO catsDAO;

    @RequestMapping("/")
    public String hello(Model model){
        model.addAttribute("name",cat.getName());
        return "hello";
    }

    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("status",createTable.createTableStatus());
        return "jdbc";
    }

    @RequestMapping("/insert")
    public String insert(Model model){
        model.addAttribute("status",catsDAO.insert(cat));
        return "jdbc";
    }

    @RequestMapping("/update")
    public String update(Model model){
        cat.setDescription("good");
        cat.setColor(15);
        model.addAttribute("status",catsDAO.update(cat));
        return "jdbc";
    }

    @RequestMapping("/delete")
    public String delete(Model model){
        model.addAttribute("status",catsDAO.delete(cat));
        return "jdbc";
    }

}
