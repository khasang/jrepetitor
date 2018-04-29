package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Group;
import io.khasang.jrepetitor.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Group addGroup(@RequestBody Group group) {

        return groupService.addGroup(group);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Group> getAllGroups() {

        return groupService.getAllGroups();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Group getGroupById(@PathVariable(value = "id") String id) {
        return groupService.getGroupById(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Group deleteGroup(@RequestParam(value = "id") String id) {
        return groupService.deleteGroup(Long.parseLong(id));
    }
}