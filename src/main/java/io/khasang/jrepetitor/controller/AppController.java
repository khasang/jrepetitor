package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.model.CreateTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
    private static final Logger log = LoggerFactory.getLogger(AppController.class);
    @Autowired
    private CreateTable createTable;

    @RequestMapping("/")
    public String helloPage() {
        return "cats";
    }

    @RequestMapping("/create")
    public String createTableStatus(Model model) {
        model.addAttribute("status", createTable.createTableStatus());

        return "create";
    }

    @RequestMapping(value = "/password/{password}", method = RequestMethod.GET)
    public String getPasswordHelp(@PathVariable("password") String password, Model model) {
        model.addAttribute("password", new BCryptPasswordEncoder().encode(password));

        return "pwd";
    }

    @RequestMapping("/user")
    public String getUserPage() {
        log.info("user has come in");
        return "user";
    }

    @RequestMapping("/super")
    public String getSuperAdmin() {
        log.info("super admin has come in");
        return "super_admin";
    }
}
