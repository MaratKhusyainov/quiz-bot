package ru.gb.questionapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.questionapi.domain.History;
import ru.gb.questionapi.dto.HistoryDto;
import ru.gb.questionapi.dto.UserDto;
import ru.gb.questionapi.services.HistoryService;
import ru.gb.questionapi.services.QuestionService;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }


    @PostMapping()
    public void createHistory(@RequestBody HistoryDto historyDto){
        historyService.saveOrUpdate(historyDto);
    }
}
