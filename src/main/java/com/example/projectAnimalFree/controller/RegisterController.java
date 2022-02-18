package com.example.projectAnimalFree.controller;
import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.exception.BadRequestException;
import com.example.projectAnimalFree.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute("user") UserDto userDto, Model model){
//        try{
//            UserDto user = userService.createUser(userDto);
//            model.addAttribute("user" ,user);
//            return "/home";
//        }
//        catch (BadRequestException e){
//            model.addAttribute("errorMessage",e.getMessage());
//        }
//        return "errorPage";
//
//    }

}


