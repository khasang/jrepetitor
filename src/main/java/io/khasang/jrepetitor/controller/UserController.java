package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.UserDTO;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.service.UserService;
import io.khasang.jrepetitor.utils.CreationProfileStatus;
import io.khasang.jrepetitor.utils.CreationUserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<CreationUserStatus> createUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        CreationUserStatus creationUserStatus = userService.createUser(user);
        return ResponseEntity.ok(creationUserStatus);
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
    public CreationProfileStatus setProfile(@RequestBody Profile profile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUserByLogin(currentPrincipalName);
        return userService.updateProfile(user, profile);
    }
}
