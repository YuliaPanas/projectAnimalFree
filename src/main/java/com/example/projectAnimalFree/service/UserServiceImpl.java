package com.example.projectAnimalFree.service;

import com.example.projectAnimalFree.dto.SearchRequest;
import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.entity.User;
import com.example.projectAnimalFree.entity.UserRole;
import com.example.projectAnimalFree.enums.Roles;
import com.example.projectAnimalFree.exception.BadRequestException;
import com.example.projectAnimalFree.exception.UserNotFoundException;
import com.example.projectAnimalFree.mapper.UserMapper;
import com.example.projectAnimalFree.repository.PostLikeRepository;
import com.example.projectAnimalFree.repository.UserRepository;
import com.example.projectAnimalFree.repository.UserRoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl extends AbstractService implements UserService{

    private final UserRepository userRepository;

    private final PostLikeRepository postLikeRepository;

    private final UserRoleRepository userRoleRepository;


    public UserServiceImpl(UserRepository userRepository, PostLikeRepository postLikeRepository, UserRoleRepository userRoleRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.postLikeRepository = postLikeRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDto findUserByEmail(SearchRequest searchRequest) {
        if(Objects.isNull(searchRequest)){
            throw new BadRequestException("search object can`t be null");
        }
        if(Objects.isNull(searchRequest.getEmail())|| searchRequest.getEmail().length()<1){
            throw new BadRequestException("email can`t be null or empty");
        }
        User userByEmail = userRepository.findUserByEmail(searchRequest.getEmail());

        return UserMapper.toDto(userByEmail);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new BadRequestException("email already exists");
        }
        User user = UserMapper.toEntity(userDto);
        user.setPassword(passwordEncoder().encode(userDto.getPassword()));
//        Set<UserRole> userRole = new HashSet<>();
//        userRole.add(new UserRole(Roles.ROLE_USER,user));
//        user.setRoles(userRole);
//        user.getRoles().add(new UserRole(Roles.ROLE_USER,user));
        user = userRepository.save(user);
        UserRole userRole = new UserRole(Roles.ROLE_USER,user);
        userRoleRepository.save(userRole);
        userDto = UserMapper.toDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        return UserMapper.toDto(userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found")));

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getCurrentUser() {
        UserDto authUser = getAuthUser();
        return authUser;
    }

    @Override
    public List<UserDto> findUsers(Long postId) {
        List<Long> usersNativeQuery = postLikeRepository.findUsersNativeQuery(postId);
        List<UserDto> userDtoList = new ArrayList<>();
        for (Long userId : usersNativeQuery) {
            UserDto userDto = getById(userId);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
