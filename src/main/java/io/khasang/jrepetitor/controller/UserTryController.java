package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.UserTry;
import io.khasang.jrepetitor.service.UserTryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/userTry")
public class UserTryController {
    @Autowired
    private UserTryService userTryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public UserTry addUserTry(@RequestBody UserTry userTry) {
        return userTryService.addUserTry(userTry);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<UserTry> getAllUserTrys() {
        return userTryService.getAllUserTrys();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public UserTry getUserTryById(@PathVariable(value = "id") String id) {
        // exception
        return userTryService.getUserTryById(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public UserTry deleteUserTry(@RequestParam(value = "id") String id) {
        return userTryService.deleteUserTry(Long.parseLong(id));
    }
}
