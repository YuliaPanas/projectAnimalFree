package com.example.projectAnimalFree.mapper;

import com.example.projectAnimalFree.dto.PostDto;
import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.entity.Post;


public class PostMapper {

//    public static PostDto toDto(Post post) {
//        return PostDto.builder()
//                .id(post.getId())
//                .text(post.getText())
//                .title(post.getTitle())
//                .userId(post.getUserId())
//                .build();
//    }

    public static Post toEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setPostName(postDto.getPostName());
        post.setText(postDto.getText());
        post.setUserId(postDto.getUserId());
        return post;
    }

    public static PostDto toDto(Post post, UserDto userDto) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setPostName(post.getPostName());
        postDto.setText(post.getText());
        postDto.setUserId(post.getUserId());
        postDto.setUserDto(userDto);
        return postDto;
    }
    public static PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setPostName(post.getPostName());
        postDto.setText(post.getText());
        postDto.setUserId(post.getUserId());
        return postDto;
    }
}
