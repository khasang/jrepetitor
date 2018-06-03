package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.QuestionDTOInterface;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.model.QuestionByQuizIdResponseBody;
import io.khasang.jrepetitor.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<QuestionDTOInterface> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public QuestionDTOInterface getQuestionById(@PathVariable(value = "id") String id) {
        return questionService.getQuestionById(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public QuestionDTOInterface deleteQuestion(@RequestParam(value = "id") String id) {
        return questionService.deleteQuestion(Long.parseLong(id));
    }

    @RequestMapping(value = "/add_by_quiz_id", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public QuestionDTOInterface addQuestionByQuizID(@RequestBody QuestionByQuizIdResponseBody question) {
        return questionService.addQuestionByQuizId(question);
    }

}