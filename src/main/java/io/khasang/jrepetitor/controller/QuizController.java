package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.QuizDTOInterface;
import io.khasang.jrepetitor.dto.QuizPreviewDTOInterface;
import io.khasang.jrepetitor.model.wrappers.QuizByGroupIdRequestWrapper;
import io.khasang.jrepetitor.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<QuizDTOInterface> addQuizByGroupId(@RequestBody QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper) {
        QuizDTOInterface quizDTO = quizService.createQuizByGroupID(quizByGroupIdRequestWrapper);
        if (quizDTO == null) {
            return new ResponseEntity<>(quizDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(quizDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<QuizDTOInterface> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<QuizDTOInterface> getQuizById(@PathVariable(value = "id") String id) {
        QuizDTOInterface quizDTO = quizService.getQuizById(Long.parseLong(id));
        if (quizDTO == null) {
            return new ResponseEntity<>(quizDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(quizDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<QuizDTOInterface> deleteQuiz(@RequestParam(value = "id") String id) {
        QuizDTOInterface quizDTO = quizService.deleteQuiz(Long.parseLong(id));
        if (quizDTO == null) {
            return new ResponseEntity<>(quizDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(quizDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/preview/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<QuizPreviewDTOInterface> getAllQuizzesPreview() {
        return quizService.getAllQuizzesPreview();
    }

    @RequestMapping(value = "/preview/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<QuizPreviewDTOInterface> getQuizPreviewById(@PathVariable(value = "id") String id) {
        QuizPreviewDTOInterface quizPreviewDTO = quizService.getQuizPreviewById(Long.parseLong(id));
        if (quizPreviewDTO == null) {
            return new ResponseEntity<>(quizPreviewDTO, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(quizPreviewDTO, HttpStatus.OK);
        }
    }


}