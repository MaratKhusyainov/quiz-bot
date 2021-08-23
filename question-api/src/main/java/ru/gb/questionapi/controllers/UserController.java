package ru.gb.questionapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.questionapi.dto.UserDto;
import ru.gb.questionapi.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public void createUser(@RequestBody UserDto userDto){
        userService.saveOrUpdate(userDto);
    }
}
