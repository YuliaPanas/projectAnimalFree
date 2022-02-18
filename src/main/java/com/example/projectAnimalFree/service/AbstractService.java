package com.example.projectAnimalFree.service;

import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.entity.User;
import com.example.projectAnimalFree.exception.UserNotFoundException;
import com.example.projectAnimalFree.mapper.UserMapper;
import com.example.projectAnimalFree.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AbstractService {

    private final UserRepository userRepository;

    public AbstractService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected UserDto getAuthUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User userByEmail = userRepository.findUserByEmail(login);
        if(Objects.isNull(userByEmail)){
            throw new UserNotFoundException("User not found");
        }
        return UserMapper.toDto(userByEmail);

    }



}
