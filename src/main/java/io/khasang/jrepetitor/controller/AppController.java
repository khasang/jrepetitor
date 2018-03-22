package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.DAO.CatsDAO;
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

    @RequestMapping("/auth")
    public String auth(Model model){
//        model.addAttribute("status",createTable.createTableStatus());
        return "auth";
    }
    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("status",createTable.createTableStatus());
        return "jdbc";
    }
    @RequestMapping("/truncate")
    public String drop(Model model){
        model.addAttribute("status",createTable.truncateTableStatus());
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

    @RequestMapping(value = "/password/{password}", method = RequestMethod.GET)
    public String getPasswordHelp(@PathVariable("password") String password, Model model){
        model.addAttribute("password", new BCryptPasswordEncoder().encode(password));
        return "pwd";
    }





}
