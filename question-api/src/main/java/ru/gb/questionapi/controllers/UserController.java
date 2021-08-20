package ru.gb.questionapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.questionapi.domain.User;
import ru.gb.questionapi.dto.UserDto;
import ru.gb.questionapi.services.QuestionService;
import ru.gb.questionapi.services.UserService;

import java.awt.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
//        return new ResponseEntity<>(userService.saveOrUpdate(userDto), HttpStatus.OK);
//    }

    @PostMapping()
    public void createUser(@RequestBody UserDto userDto){
        userService.saveOrUpdate(userDto);
    }



}
