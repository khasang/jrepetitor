package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Cat;
import io.khasang.jrepetitor.entity.Users;
import io.khasang.jrepetitor.service.CatService;
import io.khasang.jrepetitor.service.UserService;
import io.khasang.jrepetitor.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
 import  org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    UserDetailsService userDetailsService;

    // crud - create read update delete

    // add

    // REST GET PUT POST DELETE PATCH
    // JSON

    private  String  userGetId(){
    User userDetailsImpl = (User) SecurityContextHolder
            .getContext().getAuthentication().getPrincipal();
    String userid = userDetailsImpl.getUsername();
    return userid;}






    @RequestMapping(value = "/get/auth", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Users getUserById(){
        // exception
        return userService.getUserByName(userGetId());
    }




}

