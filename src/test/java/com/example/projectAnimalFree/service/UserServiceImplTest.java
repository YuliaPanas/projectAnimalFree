package com.example.projectAnimalFree.service;

import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.entity.User;
import com.example.projectAnimalFree.entity.UserRole;
import com.example.projectAnimalFree.enums.Roles;
import com.example.projectAnimalFree.exception.BadRequestException;
import com.example.projectAnimalFree.repository.UserRepository;
import com.example.projectAnimalFree.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  UserRoleRepository userRoleRepository;

    @Test
    void createUserWithBadRequestException() {
        UserDto userDto = new UserDto();
        userDto.setEmail("testEmail@gmail.com");

        User repositoryResult = new User();

//        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(repositoryResult);
        Mockito.when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(true);
//        UserDto result = userService.createUser(userDto);
//        assertEquals(result.getEmail(), userDto.getEmail());
        Assertions.assertThrows(BadRequestException.class, () -> {
            userService.createUser(userDto);
        });
    }

    @Test
    void createUserWith_success() {
        UserDto userDto = new UserDto();
        userDto.setEmail("testEmail@gmail.com");
        userDto.setUserName("userNameTest");
        userDto.setPassword("password");
        User repositoryResult = new User();
        repositoryResult.setEmail(userDto.getEmail());
        repositoryResult.setUserName(userDto.getUserName());
        repositoryResult.getRoles().add(new UserRole(Roles.ROLE_USER, repositoryResult));

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(repositoryResult);
        Mockito.when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
        UserDto result = userService.createUser(userDto);
        assertEquals(result.getEmail(), userDto.getEmail());
        assertEquals(result.getUserName(), userDto.getUserName());
        Set<Roles> expectedRoles = Set.of(Roles.ROLE_USER);
        assertEquals(expectedRoles, result.getRoles());

    }
}