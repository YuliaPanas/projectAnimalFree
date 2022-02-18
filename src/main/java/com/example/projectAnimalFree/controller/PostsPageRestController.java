package com.example.projectAnimalFree.controller;

import com.example.projectAnimalFree.dto.PostDto;
import com.example.projectAnimalFree.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostsPageRestController {

    private final PostService postService;

    public PostsPageRestController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/allPosts")
    public ResponseEntity<List<PostDto>> getAllPosts1(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

//    @GetMapping("/like/{postId}")
//    public ResponseEntity<PostDto> postLike(@PathVariable Long postId){
//       postService.doLikeOperation(postId);
//        return new ResponseEntity<>(postService.getById(postId), HttpStatus.OK);
//
//    }



}
