package com.example.projectAnimalFree.controller;

import com.example.projectAnimalFree.dto.EmailDto;
import com.example.projectAnimalFree.dto.PostDto;
import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.exception.BadRequestException;

import com.example.projectAnimalFree.service.PostService;
import com.example.projectAnimalFree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Controller
public class PostController {

    private final PostService postService;
    private UserService userService;



    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/createPost")
    public String createPostPage(){
        return "createPost";
    }


    @GetMapping("/like/{postId}")
    public String postsList(@PathVariable Long postId) {
        postService.doLikeOperation(postId);
        return "redirect:/post/" + postId;
    }

    @GetMapping("/post/{id}")
    public String postPage(@PathVariable("id") Long id, Model model){
        PostDto postDto = postService.getByPostId(id);
        model.addAttribute("posts",postDto);
        return "postInfo";
    }

    @GetMapping("/post/like/{id}")
    public String postLikePage(@PathVariable("id") Long id, Model model, EmailDto emailDto){
        List<UserDto> users = userService.findUsers(id);
        model.addAttribute("users",users);
//            model.addAttribute("user" ,user);
//            return "/home";
        return "listOfPeople";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        try {
            postService.deletePost(id);
        } catch (AccessDeniedException e) {
            System.out.println("AccessDeniedException");
        }
        return "redirect:/postsPage";
    }


    @GetMapping("/postsPage")
    public String getPosts() {
//        List<PostDto> allPosts = postService.getAllPosts();
//        PostDto byId = postService.getById(postId);
        return "postsPage";
    }




}
