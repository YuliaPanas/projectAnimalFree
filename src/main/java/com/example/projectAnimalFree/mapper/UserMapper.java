package com.example.projectAnimalFree.mapper;

import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.entity.User;
import com.example.projectAnimalFree.entity.UserRole;

import java.util.stream.Collectors;
//import com.example.projectAnimalFree.entity.UserRole;

public class UserMapper {

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toSet()));
        return userDto;
    }

}
