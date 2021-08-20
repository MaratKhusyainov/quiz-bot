package ru.gb.questionapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.questionapi.services.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionsController {
    private QuestionService questionService;

    @Autowired
    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneQuestionWithAnswers(@PathVariable Long id){
        return new ResponseEntity<>(questionService.findNewQuestion(id), HttpStatus.OK);
    }

}
