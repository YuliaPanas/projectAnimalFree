package com.example.projectAnimalFree.controller;

import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterRestController {

    private final UserService userService;

    public RegisterRestController(UserService userService) {
        this.userService = userService;
    }


//    @GetMapping("/register")
//    public ResponseEntity<UserDto> getPostById(@PathVariable Long postId) {
//        PostDto byId = postService.getById(postId);
//        return new ResponseEntity<>(byId, HttpStatus.OK);
//    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
