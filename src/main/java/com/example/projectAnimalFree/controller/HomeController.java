package com.example.projectAnimalFree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getPage(Model model){
        model.addAttribute("name", "User");
        return "home";

    }



}
