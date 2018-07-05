package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.ProfileDTOInterface;
import io.khasang.jrepetitor.dto.UserDTOInterface;
import io.khasang.jrepetitor.dto.impl.ProfileDTOImpl;
import io.khasang.jrepetitor.dto.impl.UserDTOImpl;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.model.wrappers.*;
import io.khasang.jrepetitor.service.UserService;
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
    public UserDTOInterface addUser(@RequestBody UserWrapperWithPresetRole user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userService.addUser(user);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<UserDTOInterface> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<UserDTOInterface> getUserById(@PathVariable(value = "id") String id) {
        UserDTOInterface userDTO = userService.getUserById(Long.parseLong(id));
        if (userDTO == null) {
            return new ResponseEntity<>(userDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<UserDTOInterface> deleteUser(@RequestParam(value = "id") String id) {
        UserDTOInterface deletedUser = userService.deleteUser(Long.parseLong(id));
        if (deletedUser == null) {
            return new ResponseEntity<>(deletedUser, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(deletedUser, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ProfileDTOInterface> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals("anonymousUser")) {
            ProfileDTOImpl profile = null;
            return new ResponseEntity<>(profile, HttpStatus.UNAUTHORIZED);
        }
        UserDTOImpl user = (UserDTOImpl) userService.getUserByLogin(currentPrincipalName);
        return new ResponseEntity<>(user.getProfile(), HttpStatus.OK);

    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<CreationProfileStatusResponseWrapper> setProfile(@RequestBody ProfileWrapper profile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals("anonymousUser")) {
            CreationProfileStatusResponseWrapper creationProfileStatusResponseWrapper = null;
            return new ResponseEntity<>(creationProfileStatusResponseWrapper, HttpStatus.UNAUTHORIZED);
        }
        CreationProfileStatusResponseWrapper creationProfileStatusResponseWrapper = userService.updateProfile(currentPrincipalName, profile);
        return new ResponseEntity<>(creationProfileStatusResponseWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<CreationUserStatusResponseWrapper> createUser(@RequestBody UserWrapper user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        CreationUserStatusResponseWrapper creationUserStatusResponseWrapper = userService.createUser(user);
        return ResponseEntity.ok(creationUserStatusResponseWrapper);
    }

    @RequestMapping(value = "/authorized", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getCurrentUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
