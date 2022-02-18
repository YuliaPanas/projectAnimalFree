package com.example.projectAnimalFree.service;
import com.example.projectAnimalFree.dto.PostDto;
import com.example.projectAnimalFree.dto.UserDto;

import javax.mail.MessagingException;
import java.nio.file.AccessDeniedException;
import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto) throws MessagingException;

    List<PostDto> getAllPosts();

    PostDto getById(Long postId);

    PostDto getByPostId(Long postId);

    void deletePost(Long id) throws AccessDeniedException;

    void doLikeOperation(long postId);




}
