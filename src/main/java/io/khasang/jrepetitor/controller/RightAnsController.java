package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.RightAns;
import io.khasang.jrepetitor.service.RightAnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RequestMapping(value = "/rightans")
public class RightAnsController {
    @Autowired
    private RightAnsService rightAnsService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public RightAns addRightAns(@RequestBody RightAns rightAns) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return rightAnsService.addRightAns(currentPrincipalName, rightAns);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<RightAns> getAllItemRightAns() {
        return rightAnsService.getAllRightAns();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public RightAns getItemRightAnsById(@PathVariable(value = "id") String id) {
        return rightAnsService.getRightAnsById(Long.parseLong(id));
    }
}