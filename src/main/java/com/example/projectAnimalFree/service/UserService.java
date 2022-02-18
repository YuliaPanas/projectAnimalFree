package com.example.projectAnimalFree.service;

import com.example.projectAnimalFree.dto.SearchRequest;
import com.example.projectAnimalFree.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findUserByEmail(SearchRequest searchRequest);

    UserDto createUser(UserDto userDto);

    List<UserDto> getAll();

    UserDto getById(Long id);

    void deleteUser (Long id);

    UserDto getCurrentUser();

    List<UserDto> findUsers(Long postId);






}
