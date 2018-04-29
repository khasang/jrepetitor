package io.khasang.jrepetitor.controller;


import io.khasang.jrepetitor.entity.Users;
import io.khasang.jrepetitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    UserDetailsService userDetailsService;

    private String loginUser() {
        User userImpl = (User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = userImpl.getUsername();
        return username;
    }


    @RequestMapping(value = "/get/auth", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Users getUserById() {
        return userService.getUserByName(loginUser());
    }


}

