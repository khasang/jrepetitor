package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.ItemRightAns;
import io.khasang.jrepetitor.service.ItemRightAnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/itemrightans")
public class ItemRightAnsController {
    @Autowired
    private ItemRightAnsService itemRightAnsService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ItemRightAns addItemRightAns(@RequestBody ItemRightAns itemRightAns){
        return itemRightAnsService.addItemRightAns(itemRightAns);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<ItemRightAns> getAllItemRightAns(){
        return itemRightAnsService.getAllItemRightAns();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ItemRightAns getItemRightAnsById(@PathVariable(value = "id") String id){
        // exception
        return itemRightAnsService.getItemRightAnsById(Long.parseLong(id));
    }
}