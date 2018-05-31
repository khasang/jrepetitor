package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.impl.UserDTO;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.service.UserService;
import io.khasang.jrepetitor.utils.CreationProfileStatus;
import io.khasang.jrepetitor.utils.CreationUserStatus;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") String id) {
        // exception
        UserDTO userDTO = userService.getUserById(Long.parseLong(id));
        if (userDTO == null) {
            return new ResponseEntity<>(userDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<User> deleteUser(@RequestParam(value = "id") String id) {
        User deletedUser = userService.deleteUser(Long.parseLong(id));
        if (deletedUser == null) {
            return new ResponseEntity<>(deletedUser, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(deletedUser, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<Profile> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals("anonymousUser")) {
            Profile profile = null;
            return new ResponseEntity<>(profile, HttpStatus.UNAUTHORIZED);
        }
        User user = userService.getUserByLogin(currentPrincipalName);
        return new ResponseEntity<>(user.getProfile(), HttpStatus.OK);

    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<CreationProfileStatus> setProfile(@RequestBody Profile profile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals("anonymousUser")) {
            CreationProfileStatus creationProfileStatus = null;
            return new ResponseEntity<>(creationProfileStatus, HttpStatus.UNAUTHORIZED);
        }
        User user = userService.getUserByLogin(currentPrincipalName);
        CreationProfileStatus creationProfileStatus = userService.updateProfile(user, profile);
        return new ResponseEntity<>(creationProfileStatus, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<CreationUserStatus> createUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        CreationUserStatus creationUserStatus = userService.createUser(user);
        return ResponseEntity.ok(creationUserStatus);
    }

    @RequestMapping(value = "/authorized", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getCurrentUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
