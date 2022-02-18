package com.example.projectAnimalFree.controller;

import com.example.projectAnimalFree.dto.PostDto;
import com.example.projectAnimalFree.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class CreatePostRestController {

    private final PostService postService;

    public CreatePostRestController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping(value = "/createPost")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) throws MessagingException {
        PostDto post = postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
}
