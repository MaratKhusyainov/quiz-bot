package ru.gb.questionapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.questionapi.dto.QuestionDto;
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
    public QuestionDto getOneQuestionWithAnswers(@PathVariable Long id){
        return questionService.findNewQuestion(id);
    }

    @GetMapping("/new/{difficult}/{quantity}")
    public void getAndSaveNewQuestionWithAnswers(@PathVariable int difficult, @PathVariable int quantity){
        questionService.getAndSaveUniqueNewQuestionsWithAnswersInDB(difficult, quantity);
    }

}
