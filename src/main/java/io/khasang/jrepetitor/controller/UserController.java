package io.khasang.jrepetitor.controller;


import io.khasang.jrepetitor.entity.Users;
import io.khasang.jrepetitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
 import  org.springframework.security.core.context.SecurityContextHolder;



@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    UserDetailsService userDetailsService;

    private  String  userGetId(){
    User userImpl = (User) SecurityContextHolder
            .getContext().getAuthentication().getPrincipal();
    String username = userImpl.getUsername();
    return username;}


    @RequestMapping(value = "/get/auth", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Users getUserById(){
        return userService.getUserByName(userGetId());
    }




}

