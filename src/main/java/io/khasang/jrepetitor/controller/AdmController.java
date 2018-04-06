package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User getUserById(@PathVariable(value = "id") String id){
        return userService.getUserById(Long.parseLong(id));
    }

    @RequestMapping(value = "/all_users", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<User> getListUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User deleteUser(@RequestParam(value = "id") String id) {
        return userService.deleteUser(Long.parseLong(id));
    }
}
