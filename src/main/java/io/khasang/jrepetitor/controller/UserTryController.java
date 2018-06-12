package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.QuizTryDTOInterface;

import io.khasang.jrepetitor.model.UserTryWrapper;
import io.khasang.jrepetitor.service.QuizTryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public QuizTryDTOInterface addQuizTry(@RequestBody UserTryWrapper userTryWrapper) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return quizTryService.createTry(userTryWrapper, currentPrincipalName);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<QuizTryDTOInterface> getAllTries() {
        return quizTryService.getAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<QuizTryDTOInterface> getById(@PathVariable(value = "id") String id) {
        QuizTryDTOInterface quizTryDTO = quizTryService.getById(Long.parseLong(id));
        if (quizTryDTO == null) {
            return new ResponseEntity<>(quizTryDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(quizTryDTO, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<QuizTryDTOInterface> deleteById(@RequestParam(value = "id") String id) {
        QuizTryDTOInterface quizTryDTO = quizTryService.deleteById(Long.parseLong(id));
        if (quizTryDTO == null) {
            return new ResponseEntity<>(quizTryDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(quizTryDTO, HttpStatus.OK);
        }
    }

}
