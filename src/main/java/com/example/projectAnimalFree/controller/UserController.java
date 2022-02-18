package com.example.projectAnimalFree.controller;

import com.example.projectAnimalFree.dto.SearchRequest;
import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.entity.User;
import com.example.projectAnimalFree.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userPage")
    public String userPage(Model model){
        UserDto currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "userInfo";
    }

    @PostMapping("/searchUser")
    public String userPageWithUser(@ModelAttribute("searchObject")SearchRequest searchRequest, Model model){
        UserDto userByEmail = userService.findUserByEmail(searchRequest);
        model.addAttribute("user",userByEmail);
        return "userInfo";
    }

    @GetMapping("/users")
    public String usersPage(Model model){
        List<UserDto> userDtoList = userService.getAll();
        model.addAttribute("userDtoList",userDtoList);
        return"users";
    }

    @GetMapping("/user/{id}")
    public String usersPage(@PathVariable("id") Long id,  Model model){
        UserDto user = userService.getById(id);
        model.addAttribute("user",user);
        return"userInfo";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }





    }
