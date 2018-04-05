package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/adm")
public class AdmController {
    @Autowired
    private UserService userService;

    @RequestMapping()
    public String createMain() {
        return "adm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/all_users", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<User> getListUsers() {
        return userService.getAllUsers();
    }
}
