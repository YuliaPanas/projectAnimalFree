package com.example.projectAnimalFree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReserveController {

    @GetMapping("/reserve")
    public String getReserve(){
        return"reserve";
    }


}
