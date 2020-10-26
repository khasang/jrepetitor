package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.QuestionDTOInterface;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.model.wrappers.QuestionByQuizIdRequestWrapper;
import io.khasang.jrepetitor.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<QuestionDTOInterface> addQuestionByQuizID(@RequestBody QuestionByQuizIdRequestWrapper question) {
        QuestionDTOInterface questionDTO = questionService.addQuestionByQuizId(question);
        if (questionDTO == null) {
            return new ResponseEntity<>(questionDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(questionDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<QuestionDTOInterface> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<QuestionDTOInterface> getQuestionById(@PathVariable(value = "id") String id) {
        QuestionDTOInterface questionDTO = questionService.getQuestionById(Long.parseLong(id));
        if (questionDTO == null) {
            return new ResponseEntity<>(questionDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(questionDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<QuestionDTOInterface> deleteQuestion(@RequestParam(value = "id") String id) {
        QuestionDTOInterface questionDTO = questionService.deleteQuestion(Long.parseLong(id));
        if (questionDTO == null) {
            return new ResponseEntity<>(questionDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(questionDTO, HttpStatus.OK);
        }
    }
}