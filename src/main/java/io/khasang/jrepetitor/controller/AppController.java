package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.model.CreateTable;
import io.khasang.jrepetitor.model.CreateUserTable;
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
    private CreateTable createTable;
    @Autowired
    private CreateUserTable createUserTable;

    @RequestMapping("/")
    public String helloPage(){
        return "cat";
    }

    @RequestMapping("/create")
    public String createTableStatus(Model model) {
        model.addAttribute("status", createTable.createTableStatus());
        return "create";
    }

    @RequestMapping("/createusers")
    public String createUserTableStatus(Model model) {
        model.addAttribute("status", createUserTable.createUserTableStatus());
        return "create";
    }

    @RequestMapping(value = "/password/{password}", method = RequestMethod.GET)
    public String getPasswordHelp(@PathVariable("password") String password, Model model) {
        model.addAttribute("password", new BCryptPasswordEncoder().encode(password));
        return "pwd";
    }

    @RequestMapping("/user")
    public String getUserPage(){
        return "user";
    }

    @RequestMapping("/users")
    public String getUsersPage(){
        return "users";
    }
}
