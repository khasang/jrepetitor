package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.QuizDTOInterface;
import io.khasang.jrepetitor.dto.QuizPreviewDTOInterface;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.model.QuizByGroupIdRequestWrapper;
import io.khasang.jrepetitor.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Quiz addQuiz(@RequestBody Quiz quiz) {

        return quizService.addQuiz(quiz);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<QuizDTOInterface> getAllQuizs() {

        return quizService.getAllQuizs();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public QuizDTOInterface getQuizById(@PathVariable(value = "id") String id) {
        return quizService.getQuizById(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Quiz deleteQuiz(@RequestParam(value = "id") String id) {

        return quizService.deleteQuiz(Long.parseLong(id));
    }

    @RequestMapping(value = "/preview/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<QuizPreviewDTOInterface> getAllQuizzesPreview() {

        return quizService.getAllQuizzesPreview();
    }

    @RequestMapping(value = "/preview/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public QuizPreviewDTOInterface getQuizPreviewById(@PathVariable(value = "id") String id) {
        return quizService.getQuizPreviewById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add_by_group_id", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public QuizDTOInterface addQuizByGroupId(@RequestBody QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper) {
        return quizService.createQuizByGroupID(quizByGroupIdRequestWrapper);
    }
}