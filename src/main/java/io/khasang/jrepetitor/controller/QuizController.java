package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.QuizDTO;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    // crud - create read update delete

    // add

    // REST GET PUT POST DELETE PATCH
    // JSON
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Quiz addQuiz(@RequestBody Quiz quiz){
        return quizService.addQuiz(quiz);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<QuizDTO> getAllQuizs(){
        return quizService.getAllQuizs();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public QuizDTO getQuizById(@PathVariable(value = "id") String id){
        // exception
        return quizService.getQuizById(Long.parseLong(id));
    }

    // localhost:8080/quiz/delete?id=5&name=Jack
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Quiz deleteQuiz(@RequestParam(value = "id") String id) {
        return quizService.deleteQuiz(Long.parseLong(id));
    }
}
