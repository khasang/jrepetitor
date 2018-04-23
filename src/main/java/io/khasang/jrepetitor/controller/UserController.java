package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.UserDTO;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userService.addUser(user);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public UserDTO getUserById(@PathVariable(value = "id") String id) {
        // exception
        return userService.getUserById(Long.parseLong(id));
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User deleteUser(@RequestParam(value = "id") String id) {
        return userService.deleteUser(Long.parseLong(id));
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Profile getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUserByLogin(currentPrincipalName);
        return user.getProfile();
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Profile setProfile(@RequestBody Profile profile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUserByLogin(currentPrincipalName);
        Profile currentProfile = user.getProfile();
        currentProfile.setName(profile.getName());
        currentProfile.setMiddlename(profile.getMiddlename());
        currentProfile.setSurname(profile.getSurname());
        currentProfile.setEmail(profile.getEmail());
        currentProfile.setPhoneNumber(profile.getPhoneNumber());
        userService.updateUser(user);
        return currentProfile;
    }
}
