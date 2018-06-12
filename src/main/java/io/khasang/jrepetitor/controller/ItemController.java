package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.ItemDTOInterface;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.model.ItemByQuestionIdRequestWrapper;
import io.khasang.jrepetitor.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ItemDTOInterface addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<ItemDTOInterface> getAllItems() {
        return itemService.getAllItems();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ItemDTOInterface> getItemById(@PathVariable(value = "id") String id) {
        ItemDTOInterface itemDTO = itemService.getItemById(Long.parseLong(id));
        if (itemDTO == null) {
            return new ResponseEntity<>(itemDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(itemDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ItemDTOInterface> deleteItem(@RequestParam(value = "id") String id) {
        ItemDTOInterface itemDTO = itemService.deleteItem(Long.parseLong(id));
        if (itemDTO == null) {
            return new ResponseEntity<>(itemDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(itemDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add_by_question_id", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<ItemDTOInterface> addByQuestionId(@RequestBody ItemByQuestionIdRequestWrapper itemByQuestionIdRequestWrapper) {
        ItemDTOInterface itemDTO = itemService.addByQuestionId(itemByQuestionIdRequestWrapper);
        if (itemDTO == null) {
            return new ResponseEntity<>(itemDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(itemDTO, HttpStatus.OK);
        }
    }

}