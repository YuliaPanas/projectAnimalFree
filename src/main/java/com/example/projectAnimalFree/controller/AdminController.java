package com.example.projectAnimalFree.controller;

import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminPage")
    public String adminPage(){
        return "adminPage";
    }







}
