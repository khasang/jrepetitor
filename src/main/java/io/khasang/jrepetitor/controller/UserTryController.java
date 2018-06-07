package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.QuizTryDTOInterface;
import io.khasang.jrepetitor.entity.QuizTry;
import io.khasang.jrepetitor.model.UserTryWrapper;
import io.khasang.jrepetitor.service.QuizTryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/try_quiz")
public class UserTryController {
    @Autowired
    QuizTryService quizTryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public void addQuizTry(@RequestBody UserTryWrapper userTryWrapper) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        quizTryService.createTry(userTryWrapper, currentPrincipalName);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<QuizTryDTOInterface> getAllTries() {
        return quizTryService.getAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public QuizTryDTOInterface getById(@PathVariable(value = "id") String id) {
        return quizTryService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public QuizTryDTOInterface deleteById(@RequestParam(value = "id") String id) {
        return quizTryService.deleteById(Long.parseLong(id));
    }
}
