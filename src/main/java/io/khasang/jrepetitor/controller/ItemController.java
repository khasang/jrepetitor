package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.ItemDTOInterface;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.model.ItemByQuestionIdResponseBody;
import io.khasang.jrepetitor.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Item addItem(@RequestBody Item item) {

        return itemService.addItem(item);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<ItemDTOInterface> getAllItems() {

        return itemService.getAllItems();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ItemDTOInterface getItemById(@PathVariable(value = "id") String id) {
        // exception
        return itemService.getItemById(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ItemDTOInterface deleteItem(@RequestParam(value = "id") String id) {

        return itemService.deleteItem(Long.parseLong(id));
    }

    @RequestMapping(value = "/add_by_question_id", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ItemDTOInterface addByQuestionId(@RequestBody ItemByQuestionIdResponseBody itemByQuestionIdResponseBody) {
        return itemService.addByQuestionId(itemByQuestionIdResponseBody);
    }
}