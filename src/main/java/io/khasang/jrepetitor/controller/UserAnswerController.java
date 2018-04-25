package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.UserAnswer;
import io.khasang.jrepetitor.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/userAnswer")
public class UserAnswerController {
    @Autowired
    private UserAnswerService userAnswerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public UserAnswer addUserAnswer(@RequestBody UserAnswer userAnswer){
        return userAnswerService.addUserAnswer(userAnswer);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<UserAnswer> getAllUserAnswers(){
        return userAnswerService.getAllUserAnswers();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public UserAnswer getUserAnswerById(@PathVariable(value = "id") String id){
        // exception
        return userAnswerService.getUserAnswerById(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public UserAnswer deleteUserAnswer(@RequestParam(value = "id") String id) {
        return userAnswerService.deleteUserAnswer(Long.parseLong(id));
    }
}